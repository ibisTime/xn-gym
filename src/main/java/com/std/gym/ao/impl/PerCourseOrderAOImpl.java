package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.AmountUtil;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Account;
import com.std.gym.domain.Coach;
import com.std.gym.domain.PerCourse;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.domain.SYSConfig;
import com.std.gym.domain.User;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EPayType;
import com.std.gym.enums.EPerCourseOrderStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.ESysConfigCkey;
import com.std.gym.enums.ESysUser;
import com.std.gym.enums.ESystemCode;
import com.std.gym.exception.BizException;

@Service
public class PerCourseOrderAOImpl implements IPerCourseOrderAO {
    static Logger logger = Logger.getLogger(PerCourseOrderAOImpl.class);

    @Autowired
    private IPerCourseOrderBO perCourseOrderBO;

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICoachBO coachBO;

    @Override
    public String commitOrder(String applyUser, String address, String mobile,
            String perCourseCode, Integer quantity, String applyNote) {
        PerCourse perCourse = perCourseBO.getPerCourse(perCourseCode);

        // 计算今天是周几
        Integer weekDay = DateUtil.getDayofweek(DateUtil.dateToStr(new Date(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        Integer skDays = 0;// 距离下次上课天数
        Integer skCycle = StringValidater.toInteger(perCourse.getSkCycle());
        if (skCycle < weekDay) {// 下周预约
            skDays = 7 - (weekDay - skCycle);
        } else if (skCycle > weekDay) {// 本周预约
            skDays = weekDay - skCycle;
        }
        Date appointment = DateUtil.getRelativeDate(DateUtil.getTodayStart(),
            24 * 3600 * skDays);

        Long skCount = perCourseOrderBO.getTotalCount(perCourseCode,
            appointment, perCourse.getSkStartDatetime(),
            perCourse.getSkEndDatetime());
        if (skCount > 0) {
            throw new BizException("xn0000", "该课程已被预订");
        }
        Coach coach = coachBO.getCoach(perCourse.getCoachCode());
        PerCourseOrder order = new PerCourseOrder();
        String code = OrderNoGenerater.generate(EPrefixCode.PERCOURSEORDER
            .getCode());
        order.setCode(code);
        order.setPerCourseCode(perCourseCode);
        order.setAppointDatetime(appointment);
        order.setSkDatetime(perCourse.getSkStartDatetime());
        order.setXkDatetime(perCourse.getSkEndDatetime());
        order.setAddress(address);
        order.setQuantity(quantity);
        order.setPrice(perCourse.getPrice());
        order.setAmount(perCourse.getPrice());
        order.setStatus(EPerCourseOrderStatus.NOTPAY.getCode());
        order.setApplyUser(applyUser);
        order.setMobile(mobile);
        order.setApplyDatetime(new Date());
        order.setApplyNote(applyNote);
        order.setToUser(coach.getUserId());
        perCourseOrderBO.savePerCourseOrder(order);
        return code;
    }

    @Override
    public Object toPayOrder(String orderCode, String payType) {
        Object result = null;
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (!EPerCourseOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该私课订单不处于待支付状态，不能进行支付操作");
        }

        PerCourse perCourse = perCourseBO
            .getPerCourse(order.getPerCourseCode());
        Long skCount = perCourseOrderBO.getTotalCount(order.getPerCourseCode(),
            order.getAppointDatetime(), order.getSkDatetime(),
            order.getXkDatetime());
        if (skCount > 0) {
            throw new BizException("xn0000", "该课程已被预订");
        }

        // 获取用户信息
        String userId = order.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generate(EPrefixCode.PERCOURSEORDER
            .getCode());
        perCourseOrderBO.payGroup(order, payGroup);
        if (EPayType.YE.getCode().equals(payType)) {
            result = toPayYE(order, user, payGroup, perCourse);
        } else if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayWEIXIH5(order, user, payGroup, perCourse);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;
    }

    private Object toPayYE(PerCourseOrder order, User user, String payGroup,
            PerCourse perCourse) {
        Account rmbAccount = accountBO.getRemoteAccount(order.getApplyUser(),
            ECurrency.CNY);
        if (rmbAccount.getAmount() < order.getAmount()) {
            throw new BizException("xn000000", "余额不足");
        }
        accountBO.doTransferAmountRemote(order.getApplyUser(),
            ESysUser.SYS_USER_ZWZJ.getCode(), ECurrency.CNY, order.getAmount(),
            EBizType.AJ_SKGM, EBizType.AJ_SKGM.getValue(),
            EBizType.AJ_SKGM.getValue(), order.getCode());
        paySuccess(payGroup, null, order.getAmount(), EPayType.YE.getCode());
        return new BooleanRes(true);
    }

    public Object toPayWEIXIH5(PerCourseOrder order, User user,
            String payGroup, PerCourse perCourse) {
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), ESysUser.SYS_USER_ZWZJ.getCode(), payGroup,
            order.getCode(), EBizType.AJ_SKGM, EBizType.AJ_SKGM.getValue(),
            order.getAmount());
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType) {
        PerCourseOrder order = perCourseOrderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (EPerCourseOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            // 计算违约金额
            SYSConfig sysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.WY.getCode(), ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long penalty = AmountUtil.mul(1L,
                amount * StringValidater.toDouble(sysConfig.getCvalue()));
            // 支付成功
            perCourseOrderBO.paySuccess(order, payCode, amount, penalty,
                payType);
        } else {
            logger.info("订单号：" + order.getCode() + "，已成功支付,无需重复支付");
        }
    }

    @Override
    public void receiverOrder(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (!EPerCourseOrderStatus.PAYSUCCESS.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn0000", "该私课订单不处于已支付状态，不能接单");
        }
        perCourseOrderBO.receiverOrder(order, updater, remark);
    }

    @Override
    public void classBegin(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (!EPerCourseOrderStatus.RECEIVER_ORDER.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn0000", "该私课订单不处于已接单状态，不能上课");
        }
        perCourseOrderBO.classBegin(order, updater, remark);
    }

    @Override
    public void classOver(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (!EPerCourseOrderStatus.RECEIVER_ORDER.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn0000", "该私课订单还未上课，不能下课");
        }
        perCourseOrderBO.classOver(order, updater, remark);
    }

    @Override
    public void userCancel(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (EPerCourseOrderStatus.NOTPAY.getCode().equals(order.getStatus())
                || EPerCourseOrderStatus.PAYSUCCESS.getCode().equals(
                    order.getStatus())) {
            if (EPerCourseOrderStatus.PAYSUCCESS.getCode().equals(
                order.getStatus())) {
                Date appointDatetime = DateUtil.strToDate(
                    order.getAppointDatetime() + order.getSkDatetime(),
                    DateUtil.DATA_TIME_PATTERN_1);
                if (!DateUtil.getRelativeDate(new Date(), -(60 * 60 * 2 + 1))
                    .before(appointDatetime)) {
                    throw new BizException("xn0000", "临近上课时间不到两小时,不能取消订单");
                }
                // 违约后用户能得到的钱
                Long amount = order.getAmount() - order.getPenalty();
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getApplyUser(),
                    ECurrency.CNY, amount, EBizType.AJ_SKGMTK,
                    EBizType.AJ_SKGMTK.getValue(),
                    EBizType.AJ_SKGMTK.getValue(), order.getCode());

                // 私教获得多少钱
                SYSConfig sysConfig = sysConfigBO.getConfigValue(
                    ESysConfigCkey.SJFC.getCode(),
                    ESystemCode.SYSTEM_CODE.getCode(),
                    ESystemCode.SYSTEM_CODE.getCode());
                Long coachAmount = AmountUtil.mul(1L, order.getPenalty()
                        * StringValidater.toDouble(sysConfig.getCvalue()));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getToUser(),
                    ECurrency.CNY, coachAmount, EBizType.TTJFC,
                    EBizType.TTJFC.getValue(), EBizType.TTJFC.getValue(),
                    order.getCode());
            }
            perCourseOrderBO.userCancel(order, updater, remark);
            return;
        }
        throw new BizException("xn0000", "该私课订单状态下，不能取消");
    }

    @Override
    public void platCancel(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (EPerCourseOrderStatus.USER_CANCEL.getCode().equals(
            order.getStatus())
                || EPerCourseOrderStatus.PLAT_CANCEL.getCode().equals(
                    order.getStatus())
                || EPerCourseOrderStatus.FINISH.getCode().equals(
                    order.getStatus())) {
            throw new BizException("xn0000", "该私课订单状态下，不能取消");
        }
        if (EPerCourseOrderStatus.PAYSUCCESS.getCode()
            .equals(order.getStatus())) {
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, order.getAmount(),
                EBizType.AJ_SKGMTK, EBizType.AJ_SKGMTK.getValue(),
                EBizType.AJ_SKGMTK.getValue(), order.getCode());
        }
        perCourseOrderBO.platCancel(order, updater, remark);
    }

    @Override
    public Paginable<PerCourseOrder> queryPerCourseOrderPage(int start,
            int limit, PerCourseOrder condition) {
        Paginable<PerCourseOrder> page = perCourseOrderBO.getPaginable(start,
            limit, condition);
        List<PerCourseOrder> list = page.getList();
        for (PerCourseOrder perCourseOrder : list) {
            User user = userBO.getRemoteUser(perCourseOrder.getApplyUser());
            perCourseOrder.setRealName(user.getNickname());
            Coach coach = coachBO.getCoachByUserId(perCourseOrder.getToUser());
            perCourseOrder.setCoach(coach);
        }
        return page;
    }

    @Override
    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition) {
        List<PerCourseOrder> list = perCourseOrderBO
            .queryPerCourseOrderList(condition);
        for (PerCourseOrder perCourseOrder : list) {
            User user = userBO.getRemoteUser(perCourseOrder.getApplyUser());
            perCourseOrder.setRealName(user.getNickname());
            Coach coach = coachBO.getCoachByUserId(perCourseOrder.getToUser());
            perCourseOrder.setCoach(coach);
        }
        return list;
    }

    @Override
    public PerCourseOrder getPerCourseOrder(String code) {
        PerCourseOrder perCourseOrder = perCourseOrderBO
            .getPerCourseOrder(code);
        User user = userBO.getRemoteUser(perCourseOrder.getApplyUser());
        perCourseOrder.setRealName(user.getNickname());
        Coach coach = coachBO.getCoachByUserId(perCourseOrder.getToUser());
        perCourseOrder.setCoach(coach);
        return perCourseOrder;
    }

    @Override
    public void changeOrder() {
        changeNoPayOrder();
        changePaySuccessOrder();
    }

    private void changeNoPayOrder() {
        logger.info("***************开始扫描待订单，未支付的3天后取消***************");
        PerCourseOrder condition = new PerCourseOrder();
        condition.setStatus(EPerCourseOrderStatus.NOTPAY.getCode());
        condition.setApplyBeginDatetime(DateUtil.getRelativeDate(new Date(),
            -(60 * 60 * 24 * 3 + 1)));
        List<PerCourseOrder> perCourseOrderList = perCourseOrderBO
            .queryPerCourseOrderList(condition);
        for (PerCourseOrder perCourseOrder : perCourseOrderList) {
            perCourseOrderBO.platCancel(perCourseOrder, "系统取消", "超时支付,系统自动取消");
        }
        logger.info("***************结束扫描待订单，未支付的3天后取消***************");
    }

    private void changePaySuccessOrder() {
        logger.info("***************开始扫描待订单,已支付的在上课结束后7天打款***************");
        PerCourseOrder condition = new PerCourseOrder();
        condition.setStatus(EPerCourseOrderStatus.CLASS_OVER.getCode());
        condition.setSkEndDatetime(DateUtil.getRelativeDate(new Date(),
            -(60 * 60 * 24 * 7 + 1)));
        List<PerCourseOrder> perCourseOrderList = perCourseOrderBO
            .queryPerCourseOrderList(condition);
        for (PerCourseOrder order : perCourseOrderList) {
            SYSConfig sysConfig = sysConfigBO.getConfigValue(
                ESysConfigCkey.SJFC.getCode(),
                ESystemCode.SYSTEM_CODE.getCode(),
                ESystemCode.SYSTEM_CODE.getCode());
            Long amount = AmountUtil.mul(1L, order.getAmount()
                    * StringValidater.toDouble(sysConfig.getCvalue()));
            // 给私教加钱
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getToUser(), ECurrency.CNY, amount, EBizType.AJ_SKGM,
                EBizType.AJ_SKGM.getValue(), EBizType.AJ_SKGM.getValue(),
                order.getCode());
        }
        logger.info("***************开始扫描待订单,已支付的在上课结束后7天打款***************");
    }
}
