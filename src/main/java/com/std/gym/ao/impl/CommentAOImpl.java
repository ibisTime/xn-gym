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
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECommentStatus;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EOrgCourseOrderStatus;
import com.std.gym.enums.EPerCourseOrderStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.EReaction;
import com.std.gym.enums.ESysConfigCkey;
import com.std.gym.enums.ESysConfigType;
import com.std.gym.enums.ESysUser;
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
    public String comment(String content, List<XN622200Req> itemScoreList,
            String commer, String orderCode) {
        userBO.getRemoteUser(commer);
        // 判断是否含有关键字
        String status = ECommentStatus.PUBLISHED.getCode();
        EReaction result = keywordBO.checkContent(content);
        if (EReaction.REFUSE.getCode().equals(result.getCode())) {
            status = ECommentStatus.FILTERED.getCode();
        }
        String code = null;
        if (orderCode.startsWith(EPrefixCode.PERCOURSEORDER.getCode())) {
            code = finishPerCourseOrder(orderCode, content, itemScoreList,
                status, commer);// 完成私课处理
        } else if (orderCode.startsWith(EPrefixCode.ORGCOURSEORDER.getCode())) {
            code = finishOrgCourseOrder(orderCode, content, itemScoreList,
                status, commer);// 完成团课处理
        }
        return code;
    }

    private String finishPerCourseOrder(String orderCode, String content,
            List<XN622200Req> itemScoreList, String status, String commer) {
        PerCourseOrder perCourseOrder = perCourseOrderBO
            .getPerCourseOrder(orderCode);

        String productCode = perCourseOrder.getPerCourseCode();
        if (!EPerCourseOrderStatus.CLASS_OVER.getCode().equals(
            perCourseOrder.getStatus())) {
            throw new BizException("xn0000", "该私课订单还不能评论");
        }

        perCourseOrderBO.finishOrder(perCourseOrder);
        PerCourse perCourse = perCourseBO.getPerCourse(productCode);

        Comment data = new Comment();
        String code = OrderNoGenerater.generate(EPrefixCode.COMMENT.getCode());

        // 统计分值，修改私教等级以及星数
        Double totalScore = 0.0;// 分数
        for (XN622200Req req : itemScoreList) {
            SYSConfig sysConfig = sysConfigBO.getConfigValue(req.getCkey(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());// 改动
            // 记录得分项目,及得分
            ItemScore itemScore = new ItemScore();
            itemScore.setScore(StringValidater.toInteger(req.getScore()));
            itemScore.setName(sysConfig.getRemark());
            itemScore.setCommentCode(code);
            itemScoreBO.saveItemScore(itemScore);
            double num = StringValidater.toInteger(req.getScore())
                    * StringValidater.toDouble(sysConfig.getCvalue());
            totalScore = totalScore + num;
        }
        totalScore = totalScore / itemScoreList.size();

        // 评论组装参数
        data.setCode(code);
        data.setCoachCode(perCourse.getCoachCode());
        data.setScore(totalScore.intValue());
        data.setContent(content);
        data.setCommer(commer);
        data.setCommentDatetime(new Date());
        data.setProductCode(productCode);
        data.setStatus(status);
        commentBO.saveComment(data);

        Coach coach = coachBO.getCoach(perCourse.getCoachCode());
        int starNum = coach.getStarNum() + data.getScore();// 星级数量
        List<SYSConfig> sysConfigList = sysConfigBO
            .querySYSConfigList(ESysConfigType.LEVER_RULE.getCode());
        int star = coach.getStar(); // 教练等级
        for (SYSConfig sysConfig : sysConfigList) {
            if (starNum > StringValidater.toInteger(sysConfig.getCvalue())) {
                if (ESysConfigCkey.LXJL.getCode().equals(sysConfig.getCkey())) {
                    star = 0;
                } else if (ESysConfigCkey.YXJL.getCode().equals(
                    sysConfig.getCkey())) {
                    star = 1;
                } else if (ESysConfigCkey.EXJL.getCode().equals(
                    sysConfig.getCkey())) {
                    star = 2;
                } else if (ESysConfigCkey.SAXJL.getCode().equals(
                    sysConfig.getCkey())) {
                    star = 3;
                } else if (ESysConfigCkey.SXJL.getCode().equals(
                    sysConfig.getCkey())) {
                    star = 4;
                } else if (ESysConfigCkey.WXJL.getCode().equals(
                    sysConfig.getCkey())) {
                    star = 5;
                }
            }
        }
        if (star < coach.getStar()) {
            star = coach.getStar();
        }
        coachBO.updateStar(coach, star, starNum);

        // 私课评论加积分
        SYSConfig sysConfig = sysConfigBO.getConfigValue(
            EBizType.SKGMSJF.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
            ESystemCode.SYSTEM_CODE.getCode());
        Long amount = AmountUtil.mul(1000L,
            Double.valueOf(sysConfig.getCvalue()));
        accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
            perCourseOrder.getApplyUser(), ECurrency.JF, amount,
            EBizType.SKGMSJF, EBizType.SKGMSJF.getValue(),
            EBizType.SKGMSJF.getValue(), perCourseOrder.getCode());
        return code;
    }

    private String finishOrgCourseOrder(String orderCode, String content,
            List<XN622200Req> itemScoreList, String status, String commer) {
        OrgCourseOrder orgCourseOrder = orgCourseOrderBO
            .getOrgCourseOrder(orderCode);
        if (!EOrgCourseOrderStatus.TO_COMMENT.getCode().equals(
            orgCourseOrder.getStatus())) {
            throw new BizException("xn0000", "该团课订单还不能评论");
        }
        orgCourseOrderBO.finishOrder(orgCourseOrder);
        String productCode = orgCourseOrder.getOrgCourseCode();

        Comment data = new Comment();
        String code = OrderNoGenerater.generate(EPrefixCode.COMMENT.getCode());
        // 统计分值，修改私教等级以及星数
        Double totalScore = 0.0;// 分数
        for (XN622200Req req : itemScoreList) {
            SYSConfig sysConfig = sysConfigBO.getConfigValue(req.getCkey(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());// 改动
            // 记录得分项目,及得分
            ItemScore itemScore = new ItemScore();
            itemScore.setScore(StringValidater.toInteger(req.getScore()));
            itemScore.setName(sysConfig.getRemark());
            itemScore.setCommentCode(code);
            itemScoreBO.saveItemScore(itemScore);
            double num = StringValidater.toInteger(req.getScore())
                    * StringValidater.toDouble(sysConfig.getCvalue());
            totalScore = totalScore + num;
        }
        totalScore = totalScore / itemScoreList.size();

        data.setCode(code);
        data.setScore(totalScore.intValue());
        data.setContent(content);
        data.setCommer(commer);
        data.setCommentDatetime(new Date());
        data.setProductCode(productCode);
        data.setStatus(status);
        commentBO.saveComment(data);

        OrgCourse orgCourse = orgCourseBO.getOrgCourse(productCode);
        orgCourseBO.addSumCom(orgCourse);

        // 给用户加积分
        SYSConfig sysConfig = sysConfigBO.getConfigValue(
            EBizType.KCGMSJF.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
            ESystemCode.SYSTEM_CODE.getCode());
        Long amount = AmountUtil.mul(1000L,
            Double.valueOf(sysConfig.getCvalue()));

        accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
            orgCourseOrder.getApplyUser(), ECurrency.JF, amount,
            EBizType.KCGMSJF, EBizType.KCGMSJF.getValue(),
            EBizType.KCGMSJF.getValue(), orgCourseOrder.getCode());
        return code;
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
        comment.setPhoto(user.getPhoto());
    }

    @Override
    public int avgCommentScore(String coachCode, String productCode) {
        List<Comment> list = commentBO.queryCommentList(coachCode, productCode);
        int avgNum = 0;
        int totalScore = 0;
        if (CollectionUtils.isNotEmpty(list)) {
            for (Comment comment : list) {
                totalScore = totalScore + comment.getScore();
            }
            avgNum = totalScore / list.size();
        }
        return avgNum;
    }
}
