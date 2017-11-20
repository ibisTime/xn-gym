package com.std.gym.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.IActivityOrderBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.IItemScoreBO;
import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.ISizeDataBO;
import com.std.gym.bo.ISmsOutBO;
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
import com.std.gym.domain.SizeData;
import com.std.gym.domain.User;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.dto.res.XN622920Res;
import com.std.gym.dto.res.XN622921Res;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EOrgCourseOrderStatus;
import com.std.gym.enums.EPayType;
import com.std.gym.enums.EPerCourseOrderStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.ESysConfigCkey;
import com.std.gym.enums.ESysConfigType;
import com.std.gym.enums.ESysUser;
import com.std.gym.enums.ESystemCode;
import com.std.gym.exception.BizException;
import com.std.gym.util.CalculationUtil;

@Service
public class PerCourseOrderAOImpl implements IPerCourseOrderAO {
    static Logger logger = Logger.getLogger(PerCourseOrderAOImpl.class);

    @Autowired
    private IPerCourseOrderBO perCourseOrderBO;

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IActivityOrderBO activityOrderBO;

    @Autowired
    private IOrgCourseOrderBO orgCourseOrderBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISizeDataBO sizeDataBO;

    @Autowired
    private IItemScoreBO itemScoreBO;

    @Override
    public String commitOrder(String applyUser, String address, String mobile,
            String perCourseCode, Integer quantity, String applyNote) {
        PerCourse perCourse = perCourseBO.getPerCourse(perCourseCode);

        // 判断上课人数与订单人数
        if (quantity > perCourse.getTotalNum()) {
            throw new BizException("xn0000", "预订人数不能多于课程限制人数");
        }

        Long skCount = perCourseOrderBO.getTotalCount(perCourseCode,
            perCourse.getSkStartDatetime(), perCourse.getSkEndDatetime());
        if (skCount > 0) {
            throw new BizException("xn0000", "该课程已被预订");
        }
        Coach coach = coachBO.getCoach(perCourse.getCoachCode());
        if (EBoolean.YES.getCode().equals(coach.getType())) {
            address = perCourse.getAddress();
        }
        PerCourseOrder order = new PerCourseOrder();
        String code = OrderNoGenerater.generate(EPrefixCode.PERCOURSEORDER
            .getCode());
        order.setCode(code);
        order.setType(coach.getType());
        order.setPerCourseCode(perCourseCode);
        order.setAppointDatetime(perCourse.getSkStartDatetime());
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
        order.setIsSend(EBoolean.NO.getCode());
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
            order.getSkDatetime(), order.getXkDatetime());
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
        EBizType bizType = null;
        if (EBoolean.NO.getCode().equals(order.getType())) {
            bizType = EBizType.AJ_SKGM;
        } else if (EBoolean.YES.getCode().equals(order.getType())) {
            bizType = EBizType.AJ_DRGM;
        } else {
            throw new BizException("xn0000", "未知订单类型");
        }
        accountBO.doTransferAmountRemote(order.getApplyUser(),
            ESysUser.SYS_USER_ZWZJ.getCode(), ECurrency.CNY, order.getAmount(),
            bizType, bizType.getValue(), bizType.getValue(), order.getCode());
        paySuccess(payGroup, null, order.getAmount(), EPayType.YE.getCode());
        return new BooleanRes(true);
    }

    public Object toPayWEIXIH5(PerCourseOrder order, User user,
            String payGroup, PerCourse perCourse) {
        EBizType bizType = null;
        if (EBoolean.NO.getCode().equals(order.getType())) {
            bizType = EBizType.AJ_SKGM;
        } else if (EBoolean.YES.getCode().equals(order.getType())) {
            bizType = EBizType.AJ_DRGM;
        } else {
            throw new BizException("xn0000", "未知订单类型");
        }
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), ESysUser.SYS_USER_ZWZJ.getCode(), payGroup,
            order.getCode(), bizType, bizType.getValue(), order.getAmount());
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType) {
        PerCourseOrder order = perCourseOrderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (EPerCourseOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            // 获取用户信息
            String userId = order.getApplyUser();
            User user = userBO.getRemoteUser(userId);
            // 更新课程
            PerCourse perCourse = perCourseBO.getPerCourse(order
                .getPerCourseCode());
            perCourseBO.update(perCourse, EBoolean.YES.getCode());
            // 支付成功
            perCourseOrderBO.paySuccess(order, payCode, amount, payType);
            String title = "达人";
            if (EBoolean.NO.getCode().equals(order.getType())) {
                title = "教练";
            }
            smsOutBO.sentContent(order.getToUser(), "尊敬的" + title
                    + ",您在平台上发布的课程已被" + user.getNickname()
                    + "购买,如15分钟内未处理,系统将自动为您接单。详情请登陆“自玩自健”里面查看。引起的不便,请见谅。");
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
        String title = "达人";
        if (EBoolean.NO.getCode().equals(order.getType())) {
            title = "教练";
        }
        smsOutBO.sendSmsOut(order.getMobile(),
            "尊敬的用户,您在平台上购买的订单[编号为:" + order.getCode() + "]," + title
                    + "已接单。详情请到“我的”里面查看。");
    }

    @Override
    public void classBegin(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (!EPerCourseOrderStatus.RECEIVER_ORDER.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn0000", "该私课订单不处于已接单状态，不能上课");
        }

        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 1 + 1)).before(
            order.getSkDatetime())) {
            throw new BizException("xn0000", "距离上课时间超过一小时,不能上课");
        }
        perCourseOrderBO.classBegin(order, updater, remark);
        smsOutBO.sendSmsOut(order.getMobile(), "尊敬的用户,您在平台上购买的订单" + "[编号为:"
                + order.getCode() + "],已经开始上课了。详情请到“我的”里面查看。");
    }

    @Override
    @Transactional
    public void toFullForm(String orderCode, List<SizeData> sizeDataList,
            String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (!EPerCourseOrderStatus.HAVE_CLASS.getCode().equals(
            order.getStatus())) {
            throw new BizException("xn0000", "该订单还未上课，不能填表");
        }
        for (SizeData sizeData : sizeDataList) {
            sizeData.setUserId(order.getApplyUser());
            sizeData.setOrderCode(orderCode);
            sizeDataBO.saveSizeData(sizeData);
        }
        perCourseOrderBO.toFullForm(order, updater, remark);
    }

    @Override
    public void classOver(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (EBoolean.NO.getCode().equals(order.getType())) {
            if (!EPerCourseOrderStatus.TO_FILL_FORM.getCode().equals(
                order.getStatus())) {
                throw new BizException("xn0000", "该订单还未填表，不能下课");
            }
        } else if (EBoolean.YES.getCode().equals(order.getType())) {
            if (!EPerCourseOrderStatus.HAVE_CLASS.getCode().equals(
                order.getStatus())) {
                throw new BizException("xn0000", "该订单还未上课，不能下课");
            }
        }
        perCourseOrderBO.classOver(order, updater, remark);
        smsOutBO.sendSmsOut(order.getMobile(), "尊敬的用户,您在平台上购买的订单" + "[编号为:"
                + order.getCode() + "],已经下课了。请您对此次订单进行评价,谢谢。");
    }

    @Override
    public void userCancel(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (EPerCourseOrderStatus.PAYSUCCESS.getCode()
            .equals(order.getStatus())
                || EPerCourseOrderStatus.RECEIVER_ORDER.getCode().equals(
                    order.getStatus())) {// 已支付和已接单的状态下资金划转加状态变化
            perCourseOrderBO.userCancelPay(order, updater, remark);
            // 更新课程
            PerCourse perCourse = perCourseBO.getPerCourse(order
                .getPerCourseCode());
            perCourseBO.update(perCourse, EBoolean.NO.getCode());
        } else if (EPerCourseOrderStatus.NOTPAY.getCode().equals(
            order.getStatus())) {// 未支付状态下，仅仅变动状态即可。
            perCourseOrderBO.userCancelNoPay(order, updater, remark);
        } else {// 其他状态均不准取消
            throw new BizException("xn0000", "该私课订单状态下，不能取消");
        }
    }

    @Override
    @Transactional
    public void platCancel(String orderCode, String updater, String remark) {
        PerCourseOrder order = perCourseOrderBO.getPerCourseOrder(orderCode);
        if (EPerCourseOrderStatus.USER_CANCEL.getCode().equals(
            order.getStatus())
                || EPerCourseOrderStatus.PLAT_CANCEL.getCode().equals(
                    order.getStatus())
                || EPerCourseOrderStatus.CLASS_OVER.getCode().equals(
                    order.getStatus())
                || EPerCourseOrderStatus.TO_FILL_FORM.getCode().equals(
                    order.getStatus())
                || EPerCourseOrderStatus.FINISH.getCode().equals(
                    order.getStatus())) {
            throw new BizException("xn0000", "该私课订单状态下，不能取消");
        } else if (EPerCourseOrderStatus.PAYSUCCESS.getCode().equals(
            order.getStatus())) {
            perCourseOrderBO.platCancel(order, updater, remark);
            perCourseOrderBO.platTK(order);
            // 更新课程
            PerCourse perCourse = perCourseBO.getPerCourse(order
                .getPerCourseCode());
            perCourseBO.update(perCourse, EBoolean.NO.getCode());
        } else if (EPerCourseOrderStatus.RECEIVER_ORDER.getCode().equals(
            order.getStatus())) {
            perCourseOrderBO.platCancelPenalty(order, updater, remark);
            // 接单时
            perCourseOrderBO.platTK(order);
            // 更新课程
            PerCourse perCourse = perCourseBO.getPerCourse(order
                .getPerCourseCode());
            perCourseBO.update(perCourse, EBoolean.NO.getCode());
            Account account = accountBO.getRemoteAccount(order.getToUser(),
                ECurrency.CNY);
            Coach coach = coachBO.getCoachByUserId(order.getToUser());
            if (account.getAmount() <= (-coach.getCreditAmount())) {
                coachBO.putOff(coach);
            }
        } else if (EPerCourseOrderStatus.HAVE_CLASS.getCode().equals(
            order.getStatus())) {
            // 全部退款
            perCourseOrderBO.platCancelHaveClass(order, updater, remark);
            perCourseOrderBO.platTK(order);

            Account account = accountBO.getRemoteAccount(order.getToUser(),
                ECurrency.CNY);
            Coach coach = coachBO.getCoachByUserId(order.getToUser());
            if (account.getAmount() <= (-coach.getCreditAmount())) {
                coachBO.putOff(coach);
            }
        }
        String title = "达人";
        if (EBoolean.NO.getCode().equals(order.getType())) {
            title = "教练";
        }
        smsOutBO.sentContent(order.getMobile(), "尊敬的" + title + ",您在平台上购买的订单"
                + "[编号为:" + order.getCode()
                + "],已被教练取消。详情请到“我的”里面查看。引起的不便,请见谅。");

    }

    public Paginable<PerCourseOrder> queryPerCourseOrderPage(int start,
            int limit, PerCourseOrder condition) {
        Paginable<PerCourseOrder> page = perCourseOrderBO.getPaginable(start,
            limit, condition);
        List<PerCourseOrder> list = page.getList();
        for (PerCourseOrder perCourseOrder : list) {
            this.fullPerCourseOrder(perCourseOrder);
        }
        return page;
    }

    @Override
    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition) {
        List<PerCourseOrder> list = perCourseOrderBO
            .queryPerCourseOrderList(condition);
        for (PerCourseOrder perCourseOrder : list) {
            this.fullPerCourseOrder(perCourseOrder);
        }
        return list;
    }

    private void fullPerCourseOrder(PerCourseOrder perCourseOrder) {
        User user = userBO.getRemoteUser(perCourseOrder.getApplyUser());
        perCourseOrder.setRealName(user.getNickname());
        perCourseOrder.setPhoto(user.getPhoto());
        Coach coach = coachBO.getCoachByUserId(perCourseOrder.getToUser());
        perCourseOrder.setCoach(coach);
        List<SizeData> sizeDataList = sizeDataBO
            .querySizeDataList(perCourseOrder.getCode());
        perCourseOrder.setSizeDataList(sizeDataList);
    }

    @Override
    public PerCourseOrder getPerCourseOrder(String code) {
        PerCourseOrder perCourseOrder = perCourseOrderBO
            .getPerCourseOrder(code);
        this.fullPerCourseOrder(perCourseOrder);
        return perCourseOrder;
    }

    @Override
    @Transactional
    public void changeOrder() {
        try {
            changeNoPayOrder();
        } catch (Exception e) {
            logger.info("错误" + e.getMessage());
        }

    }

    @Override
    @Transactional
    public void receiverOrder() {
        logger.info("*****************15分钟内未接单,系统开始自动接单**********************");
        try {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setStatus(EPerCourseOrderStatus.PAYSUCCESS.getCode());
            condition.setApplyBeginDatetime(DateUtil.getRelativeDate(
                new Date(), -(60 * 15 + 1)));
            List<PerCourseOrder> perCourseOrderList = perCourseOrderBO
                .queryPerCourseOrderList(condition);
            for (PerCourseOrder perCourseOrder : perCourseOrderList) {
                this.receiverOrder(perCourseOrder.getCode(),
                    perCourseOrder.getToUser(), null);
            }
        } catch (Exception e) {
            logger.info("错误" + e.getMessage());
        }
        logger.info("*****************15分钟内未接单,系统结束自动接单**********************");
    }

    @Override
    @Transactional
    public void commentOrder() {
        logger.info("******************两天未评论,系统自动开始评论************************");
        try {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setStatus(EPerCourseOrderStatus.CLASS_OVER.getCode());
            condition.setSkEndDatetime(DateUtil.getRelativeDate(new Date(),
                -(60 * 60 * 24 * 2 + 1)));
            List<PerCourseOrder> perCourseOrderList = perCourseOrderBO
                .queryPerCourseOrderList(condition);
            for (PerCourseOrder perCourseOrder : perCourseOrderList) {
                if (!EPerCourseOrderStatus.CLASS_OVER.getCode().equals(
                    perCourseOrder.getStatus())) {
                    throw new BizException("xn0000", "该私课订单还不能评论");
                }

                // 统计分值，修改私教等级以及星数
                Double totalScore = 0.0;// 分数
                double num = 0.0;
                List<SYSConfig> sysConfigList = sysConfigBO
                    .querySYSConfigList("2");// 改动
                // 记录得分项目,及得分
                for (SYSConfig sysConfig : sysConfigList) {
                    num = 5 * StringValidater.toDouble(sysConfig.getCvalue());
                }
                totalScore = totalScore + num;

                Coach coach = coachBO.getCoachByUserId(perCourseOrder
                    .getToUser());
                int starNum = coach.getStarNum() + totalScore.intValue();// 星级数量

                int star = coach.getStar(); // 教练等级
                if (EBoolean.NO.getCode().equals(perCourseOrder.getType())) {
                    List<SYSConfig> jlSysConfigList = sysConfigBO
                        .querySYSConfigList(ESysConfigType.LEVER_RULE.getCode());
                    for (SYSConfig sysConfig : jlSysConfigList) {
                        if (starNum > StringValidater.toInteger(sysConfig
                            .getCvalue())) {
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
                        EBizType.SKGMSJF.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    Long amount = AmountUtil.mul(1000L,
                        Double.valueOf(sysConfig.getCvalue()));
                    if (amount >= 10) {
                        accountBO.doTransferAmountRemote(
                            ESysUser.SYS_USER_ZWZJ.getCode(),
                            perCourseOrder.getApplyUser(), ECurrency.JF,
                            amount, EBizType.SKGMSJF,
                            EBizType.SKGMSJF.getValue(),
                            EBizType.SKGMSJF.getValue(),
                            perCourseOrder.getCode());
                    }
                    // 给私教加钱
                    SYSConfig coachSysConfig = sysConfigBO.getConfigValue(
                        ESysConfigCkey.SJFC.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    Long coachAmount = AmountUtil.mul(
                        1L,
                        perCourseOrder.getAmount()
                                * StringValidater.toDouble(coachSysConfig
                                    .getCvalue()));
                    if (coachAmount >= 10) {
                        accountBO
                            .doTransferAmountRemote(
                                ESysUser.SYS_USER_ZWZJ.getCode(),
                                perCourseOrder.getToUser(), ECurrency.CNY,
                                coachAmount, EBizType.SKJFC,
                                EBizType.SKJFC.getValue(),
                                EBizType.SKJFC.getValue(),
                                perCourseOrder.getCode());
                        smsOutBO.sentContent(
                            perCourseOrder.getToUser(),
                            "尊敬的教练,订单：[" + perCourseOrder.getCode()
                                    + "]已成功评价，收到分成"
                                    + CalculationUtil.divi(coachAmount)
                                    + "元，登录网站可查看详情。");
                    }
                } else if (EBoolean.YES.getCode().equals(
                    perCourseOrder.getType())) {
                    List<SYSConfig> drSysConfigList = sysConfigBO
                        .querySYSConfigList(ESysConfigType.DR_LEVER_RULE
                            .getCode());
                    for (SYSConfig sysConfig : drSysConfigList) {
                        if (starNum > StringValidater.toInteger(sysConfig
                            .getCvalue())) {
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
                        EBizType.DRGMSJF.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    Long amount = AmountUtil.mul(1000L,
                        Double.valueOf(sysConfig.getCvalue()));
                    if (amount >= 10) {
                        accountBO.doTransferAmountRemote(
                            ESysUser.SYS_USER_ZWZJ.getCode(),
                            perCourseOrder.getApplyUser(), ECurrency.JF,
                            amount, EBizType.DRGMSJF,
                            EBizType.DRGMSJF.getValue(),
                            EBizType.DRGMSJF.getValue(),
                            perCourseOrder.getCode());
                    }
                    // 给私教加钱
                    SYSConfig coachSysConfig = sysConfigBO.getConfigValue(
                        ESysConfigCkey.DRFC.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    Long coachAmount = AmountUtil.mul(
                        1L,
                        perCourseOrder.getAmount()
                                * StringValidater.toDouble(coachSysConfig
                                    .getCvalue()));
                    if (coachAmount >= 10) {
                        accountBO
                            .doTransferAmountRemote(
                                ESysUser.SYS_USER_ZWZJ.getCode(),
                                perCourseOrder.getToUser(), ECurrency.CNY,
                                coachAmount, EBizType.DRJFC,
                                EBizType.DRJFC.getValue(),
                                EBizType.DRJFC.getValue(),
                                perCourseOrder.getCode());
                        smsOutBO.sentContent(
                            perCourseOrder.getToUser(),
                            "尊敬的达人,订单：[" + perCourseOrder.getCode()
                                    + "]已成功评价，收到分成"
                                    + CalculationUtil.divi(coachAmount)
                                    + "元，登录网站可查看详情。");
                    }
                }
                User user = userBO.getRemoteUser(perCourseOrder.getApplyUser());
                if (StringUtils.isNotBlank(user.getUserReferee())) {
                    SYSConfig userRefereeSysConfig = sysConfigBO
                        .getConfigValue(ESysConfigCkey.HKFC.getCode(),
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
                            ESysUser.SYS_USER_ZWZJ.getCode(),
                            user.getUserReferee(), ECurrency.CNY,
                            userRefereeAmount, EBizType.TJ,
                            EBizType.TJ.getValue(), EBizType.TJ.getValue(),
                            perCourseOrder.getCode());
                    }
                }

                if (star < coach.getStar()) {
                    star = coach.getStar();
                }
                coachBO.updateStar(coach, star, starNum);
                perCourseOrderBO.finishOrder(perCourseOrder);
            }
        } catch (Exception e) {
            logger.info("错误" + e.getMessage());
        }
        logger.info("******************两天未评论,系统自动评论结束************************");
    }

    @Override
    @Transactional
    public void sendSmsOut() {
        logger.info("***************开始扫描待订单，两小时内的发送短信***************");
        try {
            PerCourseOrder condition = new PerCourseOrder();
            List<String> statusList = new ArrayList<String>();
            statusList.add(EPerCourseOrderStatus.RECEIVER_ORDER.getCode());
            statusList.add(EPerCourseOrderStatus.PAYSUCCESS.getCode());
            condition.setStatusList(statusList);
            condition.setAppointBeginDatetime(DateUtil
                .getAnyOneStart(new Date()));
            condition.setAppointEndDatetime(DateUtil.getAnyOneEnd(new Date()));
            condition.setIsSend(EBoolean.NO.getCode());
            List<PerCourseOrder> perCourseOrderList = perCourseOrderBO
                .queryPerCourseOrderList(condition);
            for (PerCourseOrder perCourseOrder : perCourseOrderList) {
                Date appointDatetime = DateUtil.strToDate(
                    DateUtil.dateToStr(perCourseOrder.getAppointDatetime(),
                        DateUtil.FRONT_DATE_FORMAT_STRING)
                            + " "
                            + perCourseOrder.getSkDatetime(),
                    DateUtil.DATA_TIME_PATTERN_1);
                if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1))
                    .after(appointDatetime)) {
                    String appoint = DateUtil.dateToStr(appointDatetime,
                        DateUtil.DATA_TIME_PATTERN_1);
                    perCourseOrderBO.updateIsSend(perCourseOrder);
                    smsOutBO.sendSmsOut(perCourseOrder.getMobile(),
                        "尊敬的用户,您在平台上购买的订单[编号为:" + perCourseOrder.getCode()
                                + "],于" + appoint
                                + "开始上课。详情请到“我的”里面查看。引起的不便,请见谅。");
                    String title = "达人";
                    if (EBoolean.NO.getCode().equals(perCourseOrder.getType())) {
                        title = "教练";
                    }
                    smsOutBO.sentContent(perCourseOrder.getToUser(), "尊敬的"
                            + title + ",您有订单[编号为:" + perCourseOrder.getCode()
                            + "],于" + appoint + "开始上课。请及时到指定地点上课。");
                }
            }
        } catch (Exception e) {
            logger.info("错误" + e.getMessage());
        }
        logger.info("***************结束扫描待订单，两小时内的发送短信***************");
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
            perCourseOrderBO.platCancelPenalty(perCourseOrder, "系统取消",
                "超时支付,系统自动取消");
            smsOutBO.sendSmsOut(perCourseOrder.getMobile(), "尊敬的用户,您在平台上购买的订单"
                    + "[编号为:" + perCourseOrder.getCode()
                    + "],3天未支付,系统已自动取消。详情请到“我的”里面查看。引起的不便,请见谅。");
        }
        logger.info("***************结束扫描待订单，未支付的3天后取消***************");
    }

    @Override
    public XN622920Res totalUnfinish(String applyUser) {
        XN622920Res res = new XN622920Res();
        List<String> statusList = new ArrayList<String>();
        // 统计活动未完成的订单
        statusList.add(EActivityOrderStatus.NOTPAY.getCode());
        statusList.add(EActivityOrderStatus.PAYSUCCESS.getCode());
        statusList.add(EActivityOrderStatus.APPLY_REFUND.getCode());
        statusList.add(EActivityOrderStatus.REFUND_NO.getCode());
        statusList.add(EActivityOrderStatus.BEGIN.getCode());
        Long actUnfinishCount = activityOrderBO.getUnfinishCount(applyUser,
            statusList);
        // 统计团课未完成的订单
        statusList.removeAll(statusList);
        statusList.add(EOrgCourseOrderStatus.NOTPAY.getCode());
        statusList.add(EOrgCourseOrderStatus.PAYSUCCESS.getCode());
        statusList.add(EOrgCourseOrderStatus.APPLY_REFUND.getCode());
        statusList.add(EOrgCourseOrderStatus.REFUND_NO.getCode());
        statusList.add(EOrgCourseOrderStatus.BEGIN.getCode());
        statusList.add(EOrgCourseOrderStatus.TO_COMMENT.getCode());
        Long orgUnfinishCount = orgCourseOrderBO.getUnfinishCount(applyUser,
            statusList);
        // 统计达人未完成的订单
        statusList.removeAll(statusList);
        statusList.add(EPerCourseOrderStatus.NOTPAY.getCode());
        statusList.add(EPerCourseOrderStatus.PAYSUCCESS.getCode());
        statusList.add(EPerCourseOrderStatus.RECEIVER_ORDER.getCode());
        statusList.add(EPerCourseOrderStatus.HAVE_CLASS.getCode());
        statusList.add(EPerCourseOrderStatus.CLASS_OVER.getCode());
        Long drUnfinishCount = perCourseOrderBO.getUnfinishCount(
            EBoolean.YES.getCode(), applyUser, statusList);
        // 统计私课未完成的订单
        statusList.add(EPerCourseOrderStatus.TO_FILL_FORM.getCode());
        Long perUnfinishCount = perCourseOrderBO.getUnfinishCount(
            EBoolean.NO.getCode(), applyUser, statusList);

        res.setActivityCount(actUnfinishCount);
        res.setOrgCourseCount(orgUnfinishCount);
        res.setPerCourseCount(perUnfinishCount);
        res.setDrCourseCount(drUnfinishCount);
        return res;
    }

    @Override
    public XN622921Res totalToComment(String applyUser) {
        XN622921Res res = new XN622921Res();
        List<String> statusList = new ArrayList<String>();
        // 统计达人未评论的订单
        statusList.add(EPerCourseOrderStatus.CLASS_OVER.getCode());
        Long dToComment = perCourseOrderBO.getUnfinishCount(
            EBoolean.YES.getCode(), applyUser, statusList);
        // 统计私课未评论的订单
        Long bToComment = perCourseOrderBO.getUnfinishCount(
            EBoolean.NO.getCode(), applyUser, statusList);
        res.setbToComment(bToComment);
        res.setdToComment(dToComment);
        return res;
    }
}
