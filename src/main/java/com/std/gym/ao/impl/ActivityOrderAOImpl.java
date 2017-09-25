package com.std.gym.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.IActivityBO;
import com.std.gym.bo.IActivityOrderBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.ISmsOutBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.AmountUtil;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Account;
import com.std.gym.domain.Activity;
import com.std.gym.domain.ActivityOrder;
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

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月12日 上午11:24:41 
 * @history:
 */
@Service
public class ActivityOrderAOImpl implements IActivityOrderAO {
    static Logger logger = Logger.getLogger(ActivityOrderAOImpl.class);

    @Autowired
    IActivityOrderBO activityOrderBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IActivityBO activityBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    ISmsOutBO smsOutBO;

    /**
     * 新增订单
     */
    @Override
    public String addActivityOrder(String activityCode, Integer quantity,
            String applyUser, String applyNote, String mobile) {
        Activity activity = activityBO.getActivity(activityCode);
        this.checkActivity(activity, quantity);

        ActivityOrder data = new ActivityOrder();
        String code = OrderNoGenerater.generate(EPrefixCode.ACTIVITYORDER
            .getCode());
        data.setCode(code);
        data.setActivityCode(activityCode);
        data.setActivityTitle(activity.getTitle());
        data.setQuantity(quantity);
        data.setPrice(activity.getAmount());
        data.setAmount(activity.getAmount() * quantity);
        data.setStatus(EActivityOrderStatus.NOTPAY.getCode());
        data.setApplyUser(applyUser);
        data.setMobile(mobile);
        data.setApplyDatetime(new Date());
        data.setApplyNote(applyNote);
        activityOrderBO.saveOrder(data);
        return code;
    }

    private void checkActivity(Activity activity, Integer quantity) {
        if (EActivityStatus.DRAFT.getCode().equals(activity.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(
                    activity.getStatus())
                || EActivityStatus.STOP.getCode().equals(activity.getStatus())
                || activity.getStartDatetime().before(new Date())) {
            throw new BizException("xn0000", "活动不在可下单范围内");
        }
        if (activity.getRemainNum() < quantity) {
            throw new BizException("xn0000", "活动名额不足");
        }
    }

    /**
     * 分页查询订单
     * @see com.cdkj.ride.ao.IOrderAO#queryActivityOrderPage(int, int, com.ActivityOrder.ride.domain.Order)
     */
    @Override
    public Paginable<ActivityOrder> queryActivityOrderPage(int start,
            int limit, ActivityOrder condition) {
        Paginable<ActivityOrder> page = activityOrderBO.getPaginable(start,
            limit, condition);
        List<ActivityOrder> orderList = page.getList();
        for (ActivityOrder order : orderList) {
            Activity activity = activityBO.getActivity(order.getActivityCode());
            order.setActivityBeginDatetime(activity.getStartDatetime());
            order.setActivityEndDatetime(activity.getEndDatetime());
            order.setPic(activity.getPic());
            order.setHoldPlace(activity.getHoldPlace());
            order.setContact(activity.getContact());
            User user = userBO.getRemoteUser(order.getApplyUser());
            order.setNickname(user.getNickname());
        }
        return page;
    }

    /**
     * 查询所有订单
     * @see com.cdkj.ride.ao.IOrderAO#queryNewsList(com.ActivityOrder.ride.domain.Order)
     */
    @Override
    public List<ActivityOrder> queryOrderList(ActivityOrder condition) {
        List<ActivityOrder> orderList = activityOrderBO
            .queryOrderList(condition);
        for (ActivityOrder order : orderList) {
            Activity activity = activityBO.getActivity(order.getActivityCode());
            order.setActivityBeginDatetime(activity.getStartDatetime());
            order.setActivityEndDatetime(activity.getEndDatetime());
            order.setPic(activity.getPic());
            order.setHoldPlace(activity.getHoldPlace());
            order.setContact(activity.getContact());
            User user = userBO.getRemoteUser(order.getApplyUser());
            order.setNickname(user.getNickname());
        }
        return orderList;
    }

    /**
     * 查询订单详情
     */
    @Override
    public ActivityOrder getActivityOrder(String code) {
        ActivityOrder order = activityOrderBO.getActivityOrder(code);
        Activity activity = activityBO.getActivity(order.getActivityCode());
        order.setActivityBeginDatetime(activity.getStartDatetime());
        order.setActivityEndDatetime(activity.getEndDatetime());
        order.setPic(activity.getPic());
        order.setHoldPlace(activity.getHoldPlace());
        order.setContact(activity.getContact());
        User user = userBO.getRemoteUser(order.getApplyUser());
        order.setNickname(user.getNickname());
        return order;
    }

    @Override
    @Transactional
    public Object orderPay(String orderCode, String payType) {
        Object result = null;
        ActivityOrder order = activityOrderBO.getActivityOrder(orderCode);
        if (!EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该活动订单不处于待支付状态，不能进行支付操作");
        }
        // 获取活动信息
        Activity activity = activityBO.getActivity(order.getActivityCode());
        this.checkActivity(activity, order.getQuantity());

        // 获取用户信息
        String userId = order.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generate(EPrefixCode.ACTIVITYORDER
            .getCode());
        activityOrderBO.payGroup(order, payGroup);
        if (order.getAmount() == 0L) {
            paySuccess(payGroup, null, order.getAmount(), null);
            return new BooleanRes(true);
        }
        if (EPayType.YE.getCode().equals(payType)) {
            result = toPayYE(order, user, payGroup, activity);
        } else if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayWEIXIH5(order, user, payGroup, activity);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;

    }

    private Object toPayYE(ActivityOrder order, User user, String payGroup,
            Activity activity) {
        Account rmbAccount = accountBO.getRemoteAccount(order.getApplyUser(),
            ECurrency.CNY);
        if (rmbAccount.getAmount() < order.getAmount()) {
            throw new BizException("xn000000", "余额不足");
        }
        accountBO.doTransferAmountRemote(order.getApplyUser(),
            ESysUser.SYS_USER_ZWZJ.getCode(), ECurrency.CNY, order.getAmount(),
            EBizType.AJ_HDGM, EBizType.AJ_HDGM.getValue(),
            EBizType.AJ_HDGM.getValue(), order.getCode());
        paySuccess(payGroup, null, order.getAmount(), EPayType.YE.getCode());
        return new BooleanRes(true);
    }

    public Object toPayWEIXIH5(ActivityOrder order, User user, String payGroup,
            Activity activity) {
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), ESysUser.SYS_USER_ZWZJ.getCode(), payGroup,
            order.getCode(), EBizType.AJ_HDGM, EBizType.AJ_HDGM.getValue(),
            order.getAmount());
    }

    @Override
    public void changeOrder() {
        changeNoPayOrder();
    }

    private void changeNoPayOrder() {
        logger.info("***************开始扫描待订单，未支付的3天后取消***************");
        ActivityOrder condition = new ActivityOrder();
        condition.setStatus(EActivityOrderStatus.NOTPAY.getCode());
        condition.setCreateBeginDatetime(DateUtil.getRelativeDate(new Date(),
            -(60 * 60 * 24 * 3 + 1)));
        List<ActivityOrder> activityOrderList = activityOrderBO
            .queryOrderList(condition);
        for (ActivityOrder activityOrder : activityOrderList) {
            activityOrderBO.platCancel(activityOrder, "系统取消", "超时支付,系统自动取消");
        }
        logger.info("***************结束扫描待订单，未支付的3天后取消***************");
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType) {
        ActivityOrder order = activityOrderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (StringUtils.isBlank(payType)) {
            payType = EPayType.WEIXIN.getCode();
        }
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            activityOrderBO.paySuccess(order, payCode, amount, payType);
            Activity activity = activityBO.getActivity(order.getActivityCode());
            if (activity.getRemainNum() - order.getQuantity() == 0) {
                activity.setStatus(EActivityStatus.STOP.getCode());
            }
            Integer remainNum = activity.getRemainNum() - order.getQuantity();
            activityBO.addSignNum(activity, remainNum);
        } else {
            logger.info("订单号：" + order.getCode() + "，已成功支付,无需重复支付");
        }
    }

    @Override
    public void userCancel(String orderCode, String updater) {
        ActivityOrder order = activityOrderBO.getActivityOrder(orderCode);
        if (!EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "该状态下不能取消订单");
        }
        activityOrderBO.userCancel(order, updater);
    }

    @Override
    public void platCancel(String orderCode, String updater, String remark) {
        ActivityOrder order = activityOrderBO.getActivityOrder(orderCode);
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            activityOrderBO.platCancel(order, updater, remark);
        }
        if (EActivityOrderStatus.PAYSUCCESS.getCode().equals(order.getStatus())) {
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, order.getAmount(),
                EBizType.AJ_HDGMTK, EBizType.AJ_HDGMTK.getValue(),
                EBizType.AJ_HDGMTK.getValue(), order.getCode());
            activityOrderBO.platCancel(order, updater, remark);
            // 取消订单人数加回,判断修改状态
            Activity activity = activityBO.getActivity(order.getActivityCode());
            activity.setStatus(EActivityStatus.ONLINE.getCode());
            activityBO.addSignNum(activity,
                activity.getRemainNum() + order.getQuantity());
            smsOutBO.sentContent(order.getApplyUser(), "尊敬的用户,您在平台上购买的活动订单"
                    + "[编号为:" + order.getCode()
                    + "],已被平台取消。详情请到“我的”里面查看。引起的不便,请见谅。");
        } else {
            throw new BizException("xn000000", "该状态下不能取消订单");
        }
    }

    @Override
    public void applyRefund(String orderCode, String applyUser, String applyNote) {
        ActivityOrder order = activityOrderBO.getActivityOrder(orderCode);
        Activity activity = activityBO.getActivity(order.getActivityCode());
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "您还没有支付,不能申请退款");
        } else if (EActivityOrderStatus.PAYSUCCESS.getCode().equals(
            order.getStatus())) {
            if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
                activity.getStartDatetime())) {
                throw new BizException("xn0000", "离活动开始不到两小时,不能申请退款");
            }
            SYSConfig sysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.ADWY.getCode(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long penalty = AmountUtil.mul(1L, order.getAmount()
                    * StringValidater.toDouble(sysConfig.getCvalue()));
            activityOrderBO.applyRefund(order, penalty, applyUser, applyNote);
        } else {
            throw new BizException("xn000000", "该状态下不能申请退款");
        }
    }

    @Override
    public void approveRefund(String orderCode, String result, String updater,
            String remark) {
        ActivityOrder order = activityOrderBO.getActivityOrder(orderCode);
        if (!EActivityOrderStatus.APPLY_REFUND.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn000000", "该状态下不能进行退款审核");
        }
        EActivityOrderStatus status = EActivityOrderStatus.REFUND_NO;
        Long penalty = 0L;
        if (EBoolean.YES.getCode().equals(result)) {
            status = EActivityOrderStatus.REFUND_YES;
            Long amount = order.getAmount() - order.getPenalty();
            penalty = order.getPenalty();
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, amount,
                EBizType.AJ_HDGMTK, EBizType.AJ_HDGMTK.getValue(),
                EBizType.AJ_HDGMTK.getValue(), order.getCode());
            // 审批通过,取消订单人数加回,判断修改状态
            Activity activity = activityBO.getActivity(order.getActivityCode());
            activity.setStatus(EActivityStatus.ONLINE.getCode());
            activityBO.addSignNum(activity,
                activity.getRemainNum() + order.getQuantity());
        }
        smsOutBO.sentContent(
            order.getApplyUser(),
            "尊敬的用户,您在平台上购买的活动订单"
                    + "[编号为:"
                    + order.getCode()
                    + "],于"
                    + DateUtil.dateToStr(order.getApplyDatetime(),
                        DateUtil.DATA_TIME_PATTERN_1) + "申请退款,经平台审核,现已"
                    + status.getValue() + "。详情请到“我的”里面查看。引起的不便,请见谅。");
        activityOrderBO.approveRefund(order, penalty, status, updater, remark);
    }

    @Override
    public void beginActivity(String activityCode, String updater, String remark) {
        Activity activity = activityBO.getActivity(activityCode);
        if (!EActivityStatus.STOP.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动还没有截止报名,还不能开始");
        }
        activityBO.beginActivity(activity, updater, remark);
        List<String> statusList = new ArrayList<String>();
        statusList.add(EActivityOrderStatus.PAYSUCCESS.getCode());
        statusList.add(EActivityOrderStatus.REFUND_NO.getCode());
        List<ActivityOrder> activityOrderList = activityOrderBO.queryOrderList(
            activityCode, statusList);
        for (ActivityOrder activityOrder : activityOrderList) {
            activityOrderBO.beginOrder(activityOrder);
        }
    }

    @Override
    public void endActivity(String activityCode, String updater, String remark) {
        Activity activity = activityBO.getActivity(activityCode);
        if (!EActivityStatus.BEGIN.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动还不能开始");
        }
        activityBO.endActivity(activity, updater, remark);
        List<String> statusList = new ArrayList<String>();
        statusList.add(EActivityOrderStatus.BEGIN.getCode());
        List<ActivityOrder> activityOrderList = activityOrderBO.queryOrderList(
            activityCode, statusList);
        this.changePaySuccessOrder(activityOrderList);
    }

    private void changePaySuccessOrder(List<ActivityOrder> orderList) {
        if (orderList != null && orderList.size() > 0) {
            for (ActivityOrder order : orderList) {
                activityOrderBO.finishOrder(order);
                // 订单完成送积分
                SYSConfig sysConfig = sysConfigBO.getConfigValue(
                    EBizType.HDGMSJF.getCode(),
                    ESystemCode.SYSTEM_CODE.getCode(),
                    ESystemCode.SYSTEM_CODE.getCode());
                Long amount = AmountUtil.mul(1000L,
                    Double.valueOf(sysConfig.getCvalue()));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getApplyUser(),
                    ECurrency.JF, amount, EBizType.HDGMSJF,
                    EBizType.HDGMSJF.getValue(), EBizType.HDGMSJF.getValue(),
                    order.getCode());
            }
        }

    }
}
