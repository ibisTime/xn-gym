package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.AmountUtil;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Account;
import com.std.gym.domain.Coach;
import com.std.gym.domain.OrgCourse;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.domain.SYSConfig;
import com.std.gym.domain.User;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EPayType;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.ESysConfigCkey;
import com.std.gym.enums.ESysUser;
import com.std.gym.enums.ESystemCode;
import com.std.gym.exception.BizException;

@Service
public class OrgCourseOrderAOImpl implements IOrgCourseOrderAO {
    static Logger logger = Logger.getLogger(OrgCourseOrderAOImpl.class);

    @Autowired
    private IOrgCourseOrderBO orgCourseOrderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOrgCourseBO orgCourseBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public String commitOrder(String orgCourseCode, Integer quantity,
            String applyUser, String mobile, String applyNote) {
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(orgCourseCode);
        userBO.getRemoteUser(applyUser);
        this.checkActivity(orgCourse, quantity);
        OrgCourseOrder data = new OrgCourseOrder();
        String code = OrderNoGenerater.generate(EPrefixCode.ORGCOURSEORDER
            .getCode());
        data.setCode(code);
        data.setOrgCourseCode(orgCourse.getCode());
        data.setOrgCourseName(orgCourse.getName());
        data.setQuantity(quantity);
        data.setPrice(orgCourse.getPrice());
        data.setAmount(orgCourse.getPrice() * quantity);
        data.setStatus(EActivityOrderStatus.NOTPAY.getCode());
        data.setApplyUser(applyUser);
        data.setMobile(mobile);
        data.setApplyDatetime(new Date());
        data.setApplyNote(applyNote);
        orgCourseOrderBO.saveOrgCourseOrder(data);
        return code;
    }

    private void checkActivity(OrgCourse orgCourse, Integer quantity) {
        if (EActivityStatus.DRAFT.getCode().equals(orgCourse.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(
                    orgCourse.getStatus())
                || EActivityStatus.END.getCode().equals(orgCourse.getStatus())) {
            throw new BizException("xn0000", "团课不在可下单范围内");
        }
        if (orgCourse.getRemainNum() < quantity) {
            throw new BizException("xn0000", "团课名额不足");
        }
    }

    @Override
    public Object toPayOrder(String orderCode, String payType) {
        Object result = null;
        OrgCourseOrder order = orgCourseOrderBO.getOrgCourseOrder(orderCode);
        if (!EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该团课订单不处于待支付状态，不能进行支付操作");
        }

        OrgCourse orgCourse = orgCourseBO
            .getOrgCourse(order.getOrgCourseCode());
        this.checkActivity(orgCourse, order.getQuantity());

        // 获取用户信息
        String userId = order.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generate(EPrefixCode.ORGCOURSEORDER
            .getCode());
        orgCourseOrderBO.payGroup(order, payGroup);
        if (EPayType.YE.getCode().equals(payType)) {
            result = toPayYE(order, user, payGroup, orgCourse);
        } else if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayWEIXIH5(order, user, payGroup, orgCourse);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;
    }

    private Object toPayYE(OrgCourseOrder order, User user, String payGroup,
            OrgCourse orgCourse) {
        Account rmbAccount = accountBO.getRemoteAccount(order.getApplyUser(),
            ECurrency.CNY);
        if (rmbAccount.getAmount() < order.getAmount()) {
            throw new BizException("xn000000", "余额不足");
        }
        accountBO.doTransferAmountRemote(order.getApplyUser(),
            ESysUser.SYS_USER_ZWZJ.getCode(), ECurrency.CNY, order.getAmount(),
            EBizType.AJ_TKGM, EBizType.AJ_TKGM.getValue(),
            EBizType.AJ_TKGM.getValue(), order.getCode());
        paySuccess(payGroup, null, order.getAmount(), EPayType.YE.getCode());
        return new BooleanRes(true);
    }

    public Object toPayWEIXIH5(OrgCourseOrder order, User user,
            String payGroup, OrgCourse orgCourse) {
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), ESysUser.SYS_USER_ZWZJ.getCode(), payGroup,
            order.getCode(), EBizType.AJ_TKGM, EBizType.AJ_TKGM.getValue(),
            order.getAmount());
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType) {
        OrgCourseOrder order = orgCourseOrderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            // 计算违约金额
            SYSConfig sysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.WY.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long penalty = AmountUtil.mul(1L,
                amount * StringValidater.toDouble(sysConfig.getCvalue()));
            // 支付成功
            orgCourseOrderBO.paySuccess(order, payCode, amount, penalty,
                payType);
            OrgCourse orgCourse = orgCourseBO.getOrgCourse(order
                .getOrgCourseCode());
            if (orgCourse.getRemainNum() - order.getQuantity() == 0) {
                orgCourse.setStatus(EActivityStatus.END.getCode());
            }
            orgCourseBO.addSignNum(orgCourse, order.getQuantity());
        } else {
            logger.info("订单号：" + order.getCode() + "，已成功支付,无需重复支付");
        }
    }

    @Override
    public void userCancel(String orderCode, String applyUser) {
        OrgCourseOrder order = orgCourseOrderBO.getOrgCourseOrder(orderCode);
        if (!EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "已支付,不可直接取消");
        }
        orgCourseOrderBO.userCancel(order, applyUser);
    }

    @Override
    public void platCancel(String orderCode, String updater, String remark) {
        OrgCourseOrder order = orgCourseOrderBO.getOrgCourseOrder(orderCode);
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orgCourseOrderBO.platCancel(order, updater, remark);
        }
        if (EActivityOrderStatus.PAYSUCCESS.getCode().equals(order.getStatus())) {
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, order.getAmount(),
                EBizType.AJ_TKGMTK, EBizType.AJ_TKGMTK.getValue(),
                EBizType.AJ_TKGMTK.getValue(), order.getCode());
            orgCourseOrderBO.platCancel(order, updater, remark);
        } else {
            throw new BizException("xn0000", "该状态下不能取消订单");
        }

    }

    @Override
    public void applyRefund(String orderCode, String applyUser, String applyNote) {
        OrgCourseOrder order = orgCourseOrderBO.getOrgCourseOrder(orderCode);
        if (!EActivityOrderStatus.PAYSUCCESS.getCode()
            .equals(order.getStatus())) {
            throw new BizException("xn0000", "该状态下不能申请退款");
        }
        OrgCourse orgCourse = orgCourseBO
            .getOrgCourse(order.getOrgCourseCode());
        if (!DateUtil.getRelativeDate(new Date(), -(60 * 60 * 2 + 1)).before(
            orgCourse.getSkStartDatetime())) {
            throw new BizException("xn0000", "临近上课时间不到两小时,不能取消订单");
        }
        orgCourseOrderBO.applyRefund(order, applyUser, applyNote);
    }

    @Override
    public void approveRefund(String orderCode, String result, String updater,
            String remark) {
        OrgCourseOrder order = orgCourseOrderBO.getOrgCourseOrder(orderCode);
        if (!EActivityOrderStatus.APPLY_REFUND.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn0000", "该状态下不能审批退款申请");
        }
        EActivityOrderStatus status = null;
        if (EBoolean.NO.getCode().equals(result)) {
            status = EActivityOrderStatus.REFUND_NO;
        } else if (EBoolean.YES.getCode().equals(result)) {
            status = EActivityOrderStatus.REFUND_YES;
            Long amount = order.getAmount() - order.getPenalty();
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, amount,
                EBizType.AJ_TKGMTK, EBizType.AJ_TKGMTK.getValue(),
                EBizType.AJ_TKGMTK.getValue(), order.getCode());
        }
        orgCourseOrderBO.approveRefund(order, status, updater, remark);
    }

    @Override
    public Paginable<OrgCourseOrder> queryOrgCourseOrderPage(int start,
            int limit, OrgCourseOrder condition) {
        Paginable<OrgCourseOrder> page = orgCourseOrderBO.getPaginable(start,
            limit, condition);
        List<OrgCourseOrder> list = page.getList();
        for (OrgCourseOrder orgCourseOrder : list) {
            OrgCourse orgCourse = orgCourseBO.getOrgCourse(orgCourseOrder
                .getOrgCourseCode());
            Coach coach = coachBO.getCoachByUserId(orgCourse.getCoachUser());
            orgCourseOrder.setOrgCourse(orgCourse);
            User user = userBO.getRemoteUser(orgCourseOrder.getApplyUser());
            orgCourseOrder.setApplyRealName(user.getNickname());
            orgCourseOrder.setCoachRealName(coach.getRealName());
        }
        return page;
    }

    @Override
    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition) {
        return orgCourseOrderBO.queryOrgCourseOrderList(condition);
    }

    @Override
    public OrgCourseOrder getOrgCourseOrder(String code) {
        OrgCourseOrder orgCourseOrder = orgCourseOrderBO
            .getOrgCourseOrder(code);
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(orgCourseOrder
            .getOrgCourseCode());
        Coach coach = coachBO.getCoachByUserId(orgCourse.getCoachUser());
        orgCourseOrder.setOrgCourse(orgCourse);
        User user = userBO.getRemoteUser(orgCourseOrder.getApplyUser());
        orgCourseOrder.setApplyRealName(user.getNickname());
        orgCourseOrder.setCoachRealName(coach.getRealName());
        return orgCourseOrder;
    }

    @Override
    public void changeOrder() {
        changeNoPayOrder();
        changeToCommentOrder();
        changePaySuccessOrder();
    }

    private void changeNoPayOrder() {
        logger.info("***************开始扫描待订单，未支付的3天后取消***************");
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setStatus(EActivityOrderStatus.NOTPAY.getCode());
        condition.setApplyBeginDatetime(DateUtil.getRelativeDate(new Date(),
            -(60 * 60 * 24 * 3 + 1)));
        List<OrgCourseOrder> orgCourseOrderList = orgCourseOrderBO
            .queryOrgCourseOrderList(condition);
        for (OrgCourseOrder orgCourseOrder : orgCourseOrderList) {
            orgCourseOrderBO.platCancel(orgCourseOrder, "系统取消", "超时支付,系统自动取消");
        }
        logger.info("***************结束扫描待订单，未支付的3天后取消***************");
    }

    private void changeToCommentOrder() {
        logger.info("***************开始扫描待订单,在上课结束后1天待评价***************");
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setStatus(EActivityOrderStatus.PAYSUCCESS.getCode());
        List<OrgCourseOrder> orgCourseOrderList = orgCourseOrderBO
            .queryOrgCourseOrderList(condition);
        for (OrgCourseOrder order : orgCourseOrderList) {
            OrgCourse orgCourse = orgCourseBO.getOrgCourse(order
                .getOrgCourseCode());
            if (orgCourse.getSkEndDatetime().before(
                DateUtil.getRelativeDate(new Date(), -(60 * 60 * 24 * 2 + 1)))) {
                orgCourseOrderBO.toComment(order);
            }
        }
        logger.info("***************开始扫描待订单,在上课结束后1天待评价***************");
    }

    private void changePaySuccessOrder() {
        logger.info("***************开始扫描待订单,已支付的在上课结束后7天打款***************");
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setStatus(EActivityOrderStatus.TO_COMMENT.getCode());
        List<OrgCourseOrder> orgCourseOrderList = orgCourseOrderBO
            .queryOrgCourseOrderList(condition);
        for (OrgCourseOrder order : orgCourseOrderList) {
            OrgCourse orgCourse = orgCourseBO.getOrgCourse(order
                .getOrgCourseCode());
            if (orgCourse.getSkEndDatetime().before(
                DateUtil.getRelativeDate(new Date(), -(60 * 60 * 24 * 7 + 1)))) {
                User user = userBO.getRemoteUser(order.getApplyUser());
                if (user.getUserReferee() != null) {
                    SYSConfig sysConfig = sysConfigBO.getConfigValue(
                        ESysConfigCkey.HKFC.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    Long amount = AmountUtil.mul(1L, order.getAmount()
                            * StringValidater.toDouble(sysConfig.getCvalue()));
                    // 给推荐人加钱
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_ZWZJ.getCode(),
                        user.getUserReferee(), ECurrency.CNY, amount,
                        EBizType.TJ, EBizType.TJ.getValue(),
                        EBizType.TJ.getValue(), order.getCode());
                    // 给团课上课教练加钱
                    SYSConfig sysConfigCoach = sysConfigBO.getConfigValue(
                        ESysConfigCkey.TTJFC.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    Long coachAmount = AmountUtil.mul(
                        1L,
                        order.getAmount()
                                * StringValidater.toDouble(sysConfigCoach
                                    .getCvalue()));
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_ZWZJ.getCode(),
                        orgCourse.getCoachUser(), ECurrency.CNY, coachAmount,
                        EBizType.TTJFC, EBizType.TTJFC.getValue(),
                        EBizType.TTJFC.getValue(), order.getCode());
                }
            }
        }
        logger.info("***************开始扫描待订单,已支付的在上课结束后7天打款***************");
    }
}
