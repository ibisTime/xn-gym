package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.std.gym.bo.ISmsOutBO;
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
import com.std.gym.util.CalculationUtil;

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

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    @Transactional
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

        int star = coach.getStar(); // 教练等级
        if (EBoolean.NO.getCode().equals(perCourseOrder.getType())) {
            List<SYSConfig> sysConfigList = sysConfigBO
                .querySYSConfigList(ESysConfigType.LEVER_RULE.getCode());
            for (SYSConfig sysConfig : sysConfigList) {
                if (starNum > StringValidater.toInteger(sysConfig.getCvalue())) {
                    if (ESysConfigCkey.LXJL.getCode().equals(
                        sysConfig.getCkey())) {
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
            // 私课评论加积分
            SYSConfig sysConfig = sysConfigBO.getConfigValue(
                EBizType.SKGMSJF.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long amount = AmountUtil.mul(1000L,
                Double.valueOf(sysConfig.getCvalue()));
            if (amount >= 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(),
                    perCourseOrder.getApplyUser(), ECurrency.JF, amount,
                    EBizType.SKGMSJF, EBizType.SKGMSJF.getValue(),
                    EBizType.SKGMSJF.getValue(), perCourseOrder.getCode());
            }
            // 给私教加钱
            SYSConfig coachSysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.SJFC.getCode(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long coachAmount = AmountUtil.mul(1L, perCourseOrder.getAmount()
                    * StringValidater.toDouble(coachSysConfig.getCvalue()));
            if (coachAmount >= 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(),
                    perCourseOrder.getToUser(), ECurrency.CNY, coachAmount,
                    EBizType.SKJFC, EBizType.SKJFC.getValue(),
                    EBizType.SKJFC.getValue(), perCourseOrder.getCode());
                smsOutBO.sentContent(perCourseOrder.getToUser(), "尊敬的教练,订单：["
                        + perCourseOrder.getCode() + "]已成功评价，收到分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
            }
        } else if (EBoolean.YES.getCode().equals(perCourseOrder.getType())) {
            List<SYSConfig> sysConfigList = sysConfigBO
                .querySYSConfigList(ESysConfigType.DR_LEVER_RULE.getCode());
            for (SYSConfig sysConfig : sysConfigList) {
                if (starNum > StringValidater.toInteger(sysConfig.getCvalue())) {
                    if (ESysConfigCkey.LXDR.getCode().equals(
                        sysConfig.getCkey())) {
                        star = 0;
                    } else if (ESysConfigCkey.YXDR.getCode().equals(
                        sysConfig.getCkey())) {
                        star = 1;
                    } else if (ESysConfigCkey.EXDR.getCode().equals(
                        sysConfig.getCkey())) {
                        star = 2;
                    } else if (ESysConfigCkey.SAXDR.getCode().equals(
                        sysConfig.getCkey())) {
                        star = 3;
                    } else if (ESysConfigCkey.SXDR.getCode().equals(
                        sysConfig.getCkey())) {
                        star = 4;
                    } else if (ESysConfigCkey.WXDR.getCode().equals(
                        sysConfig.getCkey())) {
                        star = 5;
                    }
                }
            }

            // 达人课程评论加积分
            SYSConfig sysConfig = sysConfigBO.getConfigValue(
                EBizType.DRGMSJF.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long amount = AmountUtil.mul(1000L,
                Double.valueOf(sysConfig.getCvalue()));
            if (amount >= 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(),
                    perCourseOrder.getApplyUser(), ECurrency.JF, amount,
                    EBizType.DRGMSJF, EBizType.DRGMSJF.getValue(),
                    EBizType.DRGMSJF.getValue(), perCourseOrder.getCode());
            }
            // 给私教加钱
            SYSConfig coachSysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.DRFC.getCode(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long coachAmount = AmountUtil.mul(1L, perCourseOrder.getAmount()
                    * StringValidater.toDouble(coachSysConfig.getCvalue()));
            if (coachAmount >= 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(),
                    perCourseOrder.getToUser(), ECurrency.CNY, coachAmount,
                    EBizType.DRJFC, EBizType.DRJFC.getValue(),
                    EBizType.DRJFC.getValue(), perCourseOrder.getCode());
                smsOutBO.sentContent(perCourseOrder.getToUser(), "尊敬的达人,订单：["
                        + perCourseOrder.getCode() + "]已成功评价，收到分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
            }
        }

        User user = userBO.getRemoteUser(perCourseOrder.getApplyUser());
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            SYSConfig userRefereeSysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.HKFC.getCode(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long userRefereeAmount = AmountUtil.mul(
                1L,
                perCourseOrder.getAmount()
                        * StringValidater.toDouble(userRefereeSysConfig
                            .getCvalue()));
            // 给推荐人加钱
            if (userRefereeAmount >= 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), user.getUserReferee(),
                    ECurrency.CNY, userRefereeAmount, EBizType.TJ,
                    EBizType.TJ.getValue(), EBizType.TJ.getValue(),
                    perCourseOrder.getCode());
            }
        }
        if (star < coach.getStar()) {
            star = coach.getStar();
        }
        coachBO.updateStar(coach, star, starNum);
        perCourseOrderBO.finishOrder(perCourseOrder);
        if (ECommentStatus.FILTERED.getCode().equals(status)) {
            code = code + ";filter";
        }
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
        orgCourseOrderBO.finishOrder(orgCourseOrder);

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

        User user = userBO.getRemoteUser(orgCourseOrder.getApplyUser());
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            SYSConfig userRefereeSysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.HKFC.getCode(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long userRefereeAmount = AmountUtil.mul(
                1L,
                orgCourseOrder.getAmount()
                        * StringValidater.toDouble(userRefereeSysConfig
                            .getCvalue()));
            // 给推荐人加钱
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                user.getUserReferee(), ECurrency.CNY, userRefereeAmount,
                EBizType.TJ, EBizType.TJ.getValue(), EBizType.TJ.getValue(),
                orgCourseOrder.getCode());
        }
        // 给团课上课教练加钱
        SYSConfig sysConfigCoach = sysConfigBO.getConfigValue(
            ESysConfigCkey.TTJFC.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
            ESystemCode.SYSTEM_CODE.getCode());
        Long coachAmount = AmountUtil.mul(1L, orgCourseOrder.getAmount()
                * StringValidater.toDouble(sysConfigCoach.getCvalue()));
        if (coachAmount > 0) {
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                orgCourse.getCoachUser(), ECurrency.CNY, coachAmount,
                EBizType.TTJFC, EBizType.TTJFC.getValue(),
                EBizType.TTJFC.getValue(), orgCourseOrder.getCode());
        }

        if (ECommentStatus.FILTERED.getCode().equals(status)) {
            code = code + ";filter";
        }

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
            comment.setType(coach.getType());
        }
        List<ItemScore> itemScoreList = itemScoreBO.queryItemScoreList(comment
            .getCode());
        comment.setItemScoreList(itemScoreList);
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
