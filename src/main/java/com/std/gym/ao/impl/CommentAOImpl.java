package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ICommentAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.IItemScoreBO;
import com.std.gym.bo.IKeywordBO;
import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.AmountUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.domain.Comment;
import com.std.gym.domain.ItemScore;
import com.std.gym.domain.OrgCourse;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.domain.PerCourse;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.domain.SYSConfig;
import com.std.gym.domain.User;
import com.std.gym.dto.req.XN622200Req;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECommentStatus;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EPerCourseOrderStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.EReaction;
import com.std.gym.enums.ESysAccount;
import com.std.gym.enums.ESysConfigType;
import com.std.gym.enums.ESystemCode;
import com.std.gym.exception.BizException;

@Service
public class CommentAOImpl implements ICommentAO {

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private IOrgCourseBO orgCourseBO;

    @Autowired
    private IItemScoreBO itemScoreBO;

    @Autowired
    private IPerCourseOrderBO perCourseOrderBO;

    @Autowired
    private IOrgCourseOrderBO orgCourseOrderBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String addComment(String content, List<XN622200Req> itemScoreList,
            String commer, String orderCode) {

        userBO.getRemoteUser(commer);
        String status = ECommentStatus.PUBLISHED.getCode();
        EReaction result = keywordBO.checkContent(content);
        if (EReaction.REFUSE.getCode().equals(result.getCode())) {
            status = ECommentStatus.FILTERED.getCode();
        }
        Comment data = new Comment();
        String code = OrderNoGenerater.generate(EPrefixCode.COMMENT.getCode());
        data.setCode(code);
        String productCode = null;
        if (orderCode.startsWith(EPrefixCode.PERCOURSEORDER.getCode())) {
            productCode = finishPerCourseOrder(orderCode, itemScoreList, data);
        } else if (orderCode.startsWith(EPrefixCode.ORGCOURSEORDER.getCode())) {
            productCode = finishOrgCourseOrder(orderCode);
        }

        data.setContent(content);
        data.setCommer(commer);
        data.setCommentDatetime(new Date());
        data.setProductCode(productCode);
        data.setStatus(status);
        commentBO.saveComment(data);
        return code;
    }

    private String finishOrgCourseOrder(String orderCode) {
        OrgCourseOrder orgCourseOrder = orgCourseOrderBO
            .getOrgCourseOrder(orderCode);
        if (!EActivityOrderStatus.PAYSUCCESS.getCode().equals(
            orgCourseOrder.getStatus())) {
            throw new BizException("xn0000", "该团课订单还不能评论");
        }
        orgCourseOrderBO.finishOrder(orgCourseOrder);
        SYSConfig sysConfig = sysConfigBO.getConfigValue(
            EBizType.KCGM.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
            ESystemCode.SYSTEM_CODE.getCode());
        Long amount = AmountUtil.mul(1000L,
            Double.valueOf(sysConfig.getCvalue()));
        // 给用户加积分
        accountBO.doTransferAmountRemote(ESysAccount.SYS_USER_ZWZJ.getCode(),
            orgCourseOrder.getApplyUser(), ECurrency.JF, amount, EBizType.KCGM,
            EBizType.KCGM.getValue(), EBizType.KCGM.getValue(),
            orgCourseOrder.getCode());
        return orgCourseOrder.getOrgCourseCode();
    }

    private String finishPerCourseOrder(String orderCode,
            List<XN622200Req> itemScoreList, Comment data) {
        if (CollectionUtils.isEmpty(itemScoreList)) {
            throw new BizException("xn0000", "您还没有评分");
        }
        Double score = 0.0;
        Double num = 0.0;
        Integer dfScore = 0;
        PerCourseOrder perCourseOrder = perCourseOrderBO
            .getPerCourseOrder(orderCode);
        String productCode = perCourseOrder.getPerCourseCode();
        if (!EPerCourseOrderStatus.CLASS_OVER.getCode().equals(
            perCourseOrder.getStatus())) {
            throw new BizException("xn0000", "该私课订单还不能评论");
        }
        perCourseOrderBO.finishOrder(perCourseOrder);
        PerCourse perCourse = perCourseBO.getPerCourse(productCode);
        data.setCoachCode(perCourse.getCoachCode());

        for (XN622200Req req : itemScoreList) {
            SYSConfig sysConfig = sysConfigBO.getConfig(StringValidater
                .toLong(req.getCode()));
            // 记录得分项目,及得分
            ItemScore itemScore = new ItemScore();
            itemScore.setScore(StringValidater.toInteger(req.getScore()));
            itemScore.setName(sysConfig.getNote());
            itemScore.setCommentCode(data.getCode());
            itemScoreBO.saveItemScore(itemScore);
            num = StringValidater.toInteger(req.getScore())
                    * StringValidater.toDouble(sysConfig.getCvalue());
            score = score + num;
        }
        score = score / itemScoreList.size();
        dfScore = score.intValue();
        data.setScore(dfScore);
        // 修改私教等级以及星数
        Coach coach = coachBO.getCoach(perCourse.getCoachCode());
        int starNum = coach.getStarNum() + dfScore;
        List<SYSConfig> sysConfigList = sysConfigBO
            .querySYSConfigList(ESysConfigType.LEVER_RULE.getCode());
        int star = coach.getStar();
        for (SYSConfig sysConfig : sysConfigList) {
            if (starNum > StringValidater.toInteger(sysConfig.getCvalue())) {
                star = StringValidater.toInteger(sysConfig.getRemark());
            }
        }
        if (star < coach.getStar()) {
            star = coach.getStar();
        }
        coachBO.updateStar(coach, star, starNum);
        // 加积分
        SYSConfig sysConfig = sysConfigBO.getConfigValue(
            EBizType.SKGM.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
            ESystemCode.SYSTEM_CODE.getCode());
        Long amount = AmountUtil.mul(1000L,
            Double.valueOf(sysConfig.getCvalue()));
        accountBO.doTransferAmountRemote(ESysAccount.SYS_USER_ZWZJ.getCode(),
            perCourseOrder.getApplyUser(), ECurrency.JF, amount, EBizType.SKGM,
            EBizType.SKGM.getValue(), EBizType.SKGM.getValue(),
            perCourseOrder.getCode());
        return productCode;
    }

    @Override
    public void approveComment(String code, String result, String approver,
            String remark) {
        Comment data = commentBO.getComment(code);
        if (!ECommentStatus.FILTERED.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "评论已在可审核范围内,不能审核");
        }
        String status = ECommentStatus.APPROVE_YES.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = ECommentStatus.APPROVE_NO.getCode();
        }
        commentBO.approveComment(data, status, approver, remark);
    }

    @Override
    public void dropComment(String code) {
        if (!commentBO.isCommentExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        commentBO.removeComment(code);
    }

    @Override
    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition) {
        Paginable<Comment> page = commentBO.getPaginable(start, limit,
            condition);
        List<Comment> commentList = page.getList();
        for (Comment comment : commentList) {
            this.full(comment);
        }
        return page;
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentBO.queryCommentList(condition);
    }

    @Override
    public Comment getComment(String code) {
        Comment comment = commentBO.getComment(code);
        this.full(comment);
        return comment;
    }

    private void full(Comment comment) {
        User user = userBO.getRemoteUser(comment.getCommer());
        if (comment.getProductCode()
            .startsWith(EPrefixCode.ORGCOURSE.getCode())) {
            OrgCourse orgCourse = orgCourseBO.getOrgCourse(comment
                .getProductCode());
            comment.setCourseName(orgCourse.getName());
        }
        if (comment.getProductCode()
            .startsWith(EPrefixCode.PERCOURSE.getCode())) {
            Coach coach = coachBO.getCoach(comment.getCoachCode());
            comment.setCoachRealName(coach.getRealName());
        }
        comment.setCommerRealName(user.getNickname());
    }
}
