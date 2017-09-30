package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.ISmsOutBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.common.AmountUtil;
import com.std.gym.common.DateUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dao.IPerCourseOrderDAO;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.domain.SYSConfig;
import com.std.gym.domain.User;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EPerCourseOrderStatus;
import com.std.gym.enums.ESysConfigCkey;
import com.std.gym.enums.ESysUser;
import com.std.gym.enums.ESystemCode;
import com.std.gym.exception.BizException;
import com.std.gym.util.CalculationUtil;

@Component
public class PerCourseOrderBOImpl extends PaginableBOImpl<PerCourseOrder>
        implements IPerCourseOrderBO {

    @Autowired
    private IPerCourseOrderDAO perCourseOrderDAO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    public boolean isPerCourseOrderExist(String code) {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setCode(code);
        if (perCourseOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void savePerCourseOrder(PerCourseOrder data) {
        perCourseOrderDAO.insert(data);
    }

    @Override
    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition) {
        return perCourseOrderDAO.selectList(condition);
    }

    @Override
    public PerCourseOrder getPerCourseOrder(String code) {
        PerCourseOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setCode(code);
            data = perCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public void payGroup(PerCourseOrder order, String payGroup) {
        order.setPayGroup(payGroup);
        perCourseOrderDAO.payGroup(order);
    }

    @Override
    public PerCourseOrder getOrderPayGroup(String payGroup) {
        PerCourseOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setPayGroup(payGroup);
            data = perCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "支付组号不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(PerCourseOrder order, String payCode, Long amount,
            String payType) {
        order.setStatus(EPerCourseOrderStatus.PAYSUCCESS.getCode());
        order.setPayType(payType);
        order.setPayAmount(amount);
        order.setPayCode(payCode);
        order.setPayDatetime(new Date());
        perCourseOrderDAO.paySuccess(order);
    }

    @Override
    public void receiverOrder(PerCourseOrder order, String updater,
            String remark) {
        order.setStatus(EPerCourseOrderStatus.RECEIVER_ORDER.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.receiverOrder(order);
    }

    @Override
    public void classBegin(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.HAVE_CLASS.getCode());
        order.setSkStartDatetime(new Date());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.classBegin(order);
    }

    @Override
    public void classOver(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.CLASS_OVER.getCode());
        order.setSkEndDatetime(new Date());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.classOver(order);
    }

    @Override
    public void userCancelPay(PerCourseOrder order, String updater,
            String remark) {
        Map<String, String> map = sysConfigBO.querySYSConfigMap("4");
        String appoint = DateUtil.dateToStr(order.getAppointDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        Date appointDatetime = DateUtil
            .strToDate(appoint + " " + order.getSkDatetime(),
                DateUtil.DATA_TIME_PATTERN_1);
        Long penalty = this.private1(order, updater, remark, map,
            appointDatetime);
        // 1.用户退款
        this.private2(order, penalty);
        // 2.开始分成
        this.private3(order, penalty, map, appointDatetime);// 推荐人分成
        this.private4(order, penalty, map, appointDatetime);// 达人或教练分成
    }

    private void private4(PerCourseOrder order, Long penalty,
            Map<String, String> map, Date appointDatetime) {
        // 类型（0、私教订单。1、达人订单）
        if (EBoolean.NO.getCode().equals(order.getType())) {
            this.private6(order, map, penalty, appointDatetime);
        } else if (EBoolean.YES.getCode().equals(order.getType())) {
            this.private5(order, map, penalty, appointDatetime);
        }
    }

    private void private6(PerCourseOrder order, Map<String, String> map,
            Long penalty, Date appointDatetime) {
        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
            appointDatetime)) {// 两小时后违约金
            // 私教获得多少钱
            Long coachAmount = AmountUtil.mul(penalty, StringValidater
                .toDouble(map.get(ESysConfigCkey.QSJFC.getCode())));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getToUser(), ECurrency.CNY, coachAmount, EBizType.WYSJFC,
                "两小时内用户违约,获得分成", "两小时内用户违约,获得分成", order.getCode());
            smsOutBO.sentContent(order.getToUser(),
                "尊敬的教练,由于订单：[" + order.getCode() + "]在两小时内已取消，您收到分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
        } else {// 两小时前违约金
            // 私教获得多少钱
            Long coachAmount = AmountUtil.mul(penalty, StringValidater
                .toDouble(map.get(ESysConfigCkey.WYSJFC.getCode())));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getToUser(), ECurrency.CNY, coachAmount, EBizType.WYSJFC,
                "两小时前用户违约,获得分成", "两小时前用户违约,获得分成", order.getCode());
            smsOutBO.sentContent(order.getToUser(),
                "尊敬的教练,由于订单：[" + order.getCode() + "]在两小时前已取消，您收到分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
        }
    }

    private void private5(PerCourseOrder order, Map<String, String> map,
            Long penalty, Date appointDatetime) {
        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
            appointDatetime)) {// 两小时后违约金
            // 达人获得多少钱
            Long coachAmount = AmountUtil.mul(penalty, StringValidater
                .toDouble(map.get(ESysConfigCkey.QDRFC.getCode())));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getToUser(), ECurrency.CNY, coachAmount, EBizType.WYDRFC,
                "两小时内用户违约,获得分成", "两小时内用户违约,获得分成", order.getCode());
            smsOutBO.sentContent(order.getToUser(),
                "尊敬的达人,由于订单：[" + order.getCode() + "]在两小时内已取消，您收到分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
        } else {
            // 达人获得多少钱
            Long coachAmount = AmountUtil.mul(penalty, StringValidater
                .toDouble(map.get(ESysConfigCkey.WYDRFC.getCode())));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getToUser(), ECurrency.CNY, coachAmount, EBizType.WYDRFC,
                "两小时前用户违约,获得分成", "两小时前用户违约,获得分成", order.getCode());
            smsOutBO.sentContent(order.getToUser(),
                "尊敬的达人,由于订单：[" + order.getCode() + "]在两小时前已取消，您收到分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
        }
    }

    private void private3(PerCourseOrder order, Long penalty,
            Map<String, String> map, Date appointDatetime) {
        // 推荐人获得多少钱
        User user = userBO.getRemoteUser(order.getApplyUser());
        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
            appointDatetime)) {// 两小时后违约金
            // 推荐人获得多少钱
            if (StringUtils.isNotEmpty(user.getUserReferee())) {
                double tjRate = StringValidater.toDouble(map
                    .get(ESysConfigCkey.QHKFC.getCode()));
                Long coachAmount = AmountUtil.mul(penalty, tjRate);
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), user.getUserReferee(),
                    ECurrency.CNY, coachAmount, EBizType.TJ,
                    EBizType.TJ.getValue(), EBizType.TJ.getValue(),
                    order.getCode());
                smsOutBO.sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                        + order.getCode() + "]在两小时内已取消，您收到推荐分成"
                        + CalculationUtil.divi(coachAmount) + "元，登录网站可查看详情。");
            }
        } else {
            if (StringUtils.isNotEmpty(user.getUserReferee())) {
                double hkRate = StringValidater.toDouble(map
                    .get(ESysConfigCkey.LHKFC.getCode()));
                Long hkAmount = AmountUtil.mul(penalty, hkRate);
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), user.getUserReferee(),
                    ECurrency.CNY, hkAmount, EBizType.TJ,
                    EBizType.TJ.getValue(), EBizType.TJ.getValue(),
                    order.getCode());
                smsOutBO.sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                        + order.getCode() + "]在两小时前已取消，您收到推荐分成"
                        + CalculationUtil.divi(hkAmount) + "元，登录网站可查看详情。");
            }
        }
    }

    private Long private1(PerCourseOrder order, String updater, String remark,
            Map<String, String> map, Date appointDatetime) {
        // 计算违约金额
        Long penalty = 0L;
        double wyRate = 0.0D;
        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
            appointDatetime)) {
            wyRate = StringValidater.toDouble(map.get(ESysConfigCkey.QWY
                .getCode()));
            penalty = AmountUtil.mul(order.getAmount(), wyRate);
        } else {
            wyRate = StringValidater.toDouble(map.get(ESysConfigCkey.WY
                .getCode()));
            penalty = AmountUtil.mul(order.getAmount(), wyRate);
        }
        order.setPreStatus(order.getStatus());
        order.setStatus(EPerCourseOrderStatus.USER_CANCEL.getCode());
        order.setPenalty(penalty);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.userCancelOrder(order);
        return penalty;
    }

    private void private2(PerCourseOrder order, Long penalty) {
        // 违约后用户能得到的钱
        Long amount = order.getAmount() - penalty;
        EBizType bizType = EBizType.AJ_SKGMTK;
        if (EBoolean.YES.getCode().equals(order.getType())) {
            bizType = EBizType.AJ_DRGMTK;
        }
        accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
            order.getApplyUser(), ECurrency.CNY, amount, bizType,
            bizType.getValue(), bizType.getValue(), order.getCode());
    }

    public void userCancelNoPay(PerCourseOrder order, String updater,
            String remark) {
        order.setPreStatus(order.getStatus());
        order.setStatus(EPerCourseOrderStatus.USER_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.userCancelOrder(order);
    }

    @Override
    public void platCancelPenalty(PerCourseOrder order, String updater,
            String remark) {
        Map<String, String> map = sysConfigBO.querySYSConfigMap("4");
        String appoint = DateUtil.dateToStr(order.getAppointDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        Date appointDatetime = DateUtil
            .strToDate(appoint + " " + order.getSkDatetime(),
                DateUtil.DATA_TIME_PATTERN_1);
        Long penalty = this.private7(order, updater, remark, map,
            appointDatetime);
        // 2.开始分成
        this.private8(order, penalty, map, appointDatetime);// 推荐人分成
        this.private9(order, penalty, map, appointDatetime);// 用户分成
    }

    private void private9(PerCourseOrder order, Long penalty,
            Map<String, String> map, Date appointDatetime) {
        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
            appointDatetime)) {// 两小时后的
            if (EBoolean.NO.getCode().equals(order.getType())) {
                // 私教违约,用户获得多少钱
                Long coachAmount = AmountUtil.mul(penalty, StringValidater
                    .toDouble(map.get(ESysConfigCkey.QBWYYHFC.getCode())));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getApplyUser(),
                    ECurrency.CNY, coachAmount, EBizType.BWYYHFCY,
                    "两小时内教练违约,获得违约金", "两小时内教练违约,获得违约金", order.getCode());
            } else {
                // 达人违约,用户获得多少钱
                Long coachAmount = AmountUtil.mul(penalty, StringValidater
                    .toDouble(map.get(ESysConfigCkey.QDWYYHFC.getCode())));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getApplyUser(),
                    ECurrency.CNY, coachAmount, EBizType.QWYYHFCY,
                    "两小时内达人违约,获得分成", "两小时内达人违约,获得分成", order.getCode());
            }
        } else {// 两小时前的
            if (EBoolean.NO.getCode().equals(order.getType())) {
                // 用户除了退款还会获得多少违约金
                Long penaltyAmount = AmountUtil.mul(penalty, StringValidater
                    .toDouble(map.get(ESysConfigCkey.BWYYHFC.getCode())));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getApplyUser(),
                    ECurrency.CNY, penaltyAmount, EBizType.BWYYHFCY,
                    "两小时前教练违约,用户获得违约分成", "两小时前教练违约,用户获得违约分成", order.getCode());
            } else {
                // 用户除了退款还会获得多少违约金
                Long penaltyAmount = AmountUtil.mul(penalty, StringValidater
                    .toDouble(map.get(ESysConfigCkey.MWYYHFC.getCode())));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), order.getApplyUser(),
                    ECurrency.CNY, penaltyAmount, EBizType.QWYYHFCY,
                    "两小时前达人违约,获得分成", "两小时前达人违约,获得分成", order.getCode());
            }
        }
    }

    private void private8(PerCourseOrder order, Long penalty,
            Map<String, String> map, Date appointDatetime) {
        User user = userBO.getRemoteUser(order.getApplyUser());
        Long tjWyAmount = 0L;
        if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
            appointDatetime)) {// 两小时后的
            if (EBoolean.NO.getCode().equals(order.getType())) {
                if (StringUtils.isNotEmpty(user.getUserReferee())) {
                    // 推荐人获得多少钱
                    SYSConfig tj = sysConfigBO.getConfigValue(
                        ESysConfigCkey.BLXSN.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode(),
                        ESystemCode.SYSTEM_CODE.getCode());
                    tjWyAmount = AmountUtil.mul(penalty,
                        StringValidater.toDouble(tj.getCvalue()));
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_ZWZJ.getCode(),
                        user.getUserReferee(), ECurrency.CNY, tjWyAmount,
                        EBizType.TJ, "两小时内教练违约,获得推荐分成", "两小时内教练违约,获得推荐分成",
                        order.getCode());
                    smsOutBO
                        .sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                                + order.getCode() + "]在两小时内被教练，您收到推荐分成"
                                + CalculationUtil.divi(tjWyAmount)
                                + "元，登录网站可查看详情。");
                }
            } else {
                if (StringUtils.isNotEmpty(user.getUserReferee())) {
                    // 推荐人获得多少钱
                    tjWyAmount = AmountUtil.mul(penalty, StringValidater
                        .toDouble(map.get(ESysConfigCkey.MLXSN.getCode())));
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_ZWZJ.getCode(),
                        user.getUserReferee(), ECurrency.CNY, tjWyAmount,
                        EBizType.TJ, "两小时内达人违约,获得推荐分成", "两小时内达人违约,获得推荐分成",
                        order.getCode());
                    smsOutBO
                        .sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                                + order.getCode() + "]在两小时内已取消，您收到推荐分成"
                                + CalculationUtil.divi(tjWyAmount)
                                + "元，登录网站可查看详情。");
                }
            }
        } else {// 两小时前的
            if (EBoolean.NO.getCode().equals(order.getType())) {
                if (StringUtils.isNotEmpty(user.getUserReferee())) {
                    // 两小时前教练违约,推荐人获得多少钱
                    tjWyAmount = AmountUtil.mul(penalty, StringValidater
                        .toDouble(map.get(ESysConfigCkey.BLXSQ.getCode())));
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_ZWZJ.getCode(),
                        user.getUserReferee(), ECurrency.CNY, tjWyAmount,
                        EBizType.TJ, "两小时前教练违约,获得推荐分成", "两小时前教练违约,获得推荐分成",
                        order.getCode());
                    smsOutBO
                        .sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                                + order.getCode() + "]在距离上课两小时前已取消，您收到推荐分成"
                                + CalculationUtil.divi(tjWyAmount)
                                + "元，登录网站可查看详情。");
                }
            } else {
                if (StringUtils.isNotEmpty(user.getUserReferee())) {
                    // 两小时前达人违约,推荐人获得多少钱
                    tjWyAmount = AmountUtil.mul(penalty, StringValidater
                        .toDouble(map.get(ESysConfigCkey.MLXSQ.getCode())));
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_ZWZJ.getCode(),
                        user.getUserReferee(), ECurrency.CNY, tjWyAmount,
                        EBizType.TJ, "两小时前达人违约,获得推荐分成", "两小时前达人违约,获得推荐分成",
                        order.getCode());
                    smsOutBO
                        .sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                                + order.getCode() + "]在上课两小时前已取消，您收到推荐分成"
                                + CalculationUtil.divi(tjWyAmount)
                                + "元，登录网站可查看详情。");
                }
            }
        }
    }

    private Long private7(PerCourseOrder order, String updater, String remark,
            Map<String, String> map, Date appointDatetime) {
        // 计算违约金额
        Long penalty = 0L;
        String title = "教练";
        EBizType bizType = EBizType.BWYYHFC;
        String time = "两小时前";
        if (EBoolean.NO.getCode().equals(order.getType())) {
            title = "教练";
            bizType = EBizType.BWYYHFC;
            if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
                appointDatetime)) {
                time = "两小时内";
                penalty = AmountUtil.mul(order.getAmount(), StringValidater
                    .toDouble(map.get(ESysConfigCkey.QBWY.getCode())));
            } else {
                penalty = AmountUtil.mul(order.getAmount(), StringValidater
                    .toDouble(map.get(ESysConfigCkey.BWY.getCode())));
            }
        } else {
            title = "达人";
            bizType = EBizType.QWYYHFC;
            if (DateUtil.getRelativeDate(new Date(), (60 * 60 * 2 + 1)).after(
                appointDatetime)) {
                time = "两小时内";
                penalty = AmountUtil.mul(order.getAmount(), StringValidater
                    .toDouble(map.get(ESysConfigCkey.QMWY.getCode())));
            } else {
                penalty = AmountUtil.mul(order.getAmount(), StringValidater
                    .toDouble(map.get(ESysConfigCkey.MWY.getCode())));
            }
        }

        order.setPreStatus(order.getStatus());
        order.setCoachPenalty(penalty);
        order.setStatus(EPerCourseOrderStatus.PLAT_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.platCancelOrder(order);
        // 平台会收到多少教练违约金
        accountBO.doTransferAmountRemote(order.getToUser(),
            ESysUser.SYS_USER_ZWZJ.getCode(), ECurrency.CNY, penalty, bizType,
            time + title + "违约,扣除违约金额", time + title + "违约,获得违约金额",
            order.getCode());
        return penalty;
    }

    @Override
    public void finishOrder(PerCourseOrder perCourseOrder) {
        perCourseOrder.setStatus(EPerCourseOrderStatus.FINISH.getCode());
        perCourseOrderDAO.finishOrder(perCourseOrder);
    }

    @Override
    public Long getTotalCount(String perCourseCode, Date appointment,
            String skStartDatetime, String skEndDatetime) {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setPerCourseCode(perCourseCode);
        condition.setAppointDatetime(appointment);
        condition.setSkDatetime(skStartDatetime);
        condition.setXkDatetime(skEndDatetime);
        condition.setStatus(EPerCourseOrderStatus.PAYSUCCESS.getCode());
        return perCourseOrderDAO.selectTotalCount(condition);
    }

    @Override
    public Long getUnfinishCount(String type, String applyUser,
            List<String> statusList) {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setType(type);
        condition.setApplyUser(applyUser);
        condition.setStatusList(statusList);
        return perCourseOrderDAO.selectTotalCount(condition);
    }

    @Override
    public void toFullForm(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.TO_FILL_FORM.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.receiverOrder(order);
    }

    @Override
    public void updateIsSend(PerCourseOrder order) {
        order.setIsSend(EBoolean.YES.getCode());
        perCourseOrderDAO.updateIsSend(order);

    }

    @Override
    public void platTK(PerCourseOrder order) {
        if (EBoolean.NO.getCode().equals(order.getType())) {
            // 支付时取消,直接退款
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, order.getAmount(),
                EBizType.AJ_SKGMTK, EBizType.AJ_SKGMTK.getValue(),
                EBizType.AJ_SKGMTK.getValue(), order.getCode());
        } else {
            // 支付时取消,直接退款
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, order.getAmount(),
                EBizType.AJ_DRGMTK, EBizType.AJ_DRGMTK.getValue(),
                EBizType.AJ_DRGMTK.getValue(), order.getCode());
        }
    }

    @Override
    public void platCancel(PerCourseOrder order, String updater, String remark) {
        order.setPreStatus(order.getStatus());
        order.setStatus(EPerCourseOrderStatus.PLAT_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.platCancelOrder(order);
    }

    @Override
    public void platCancelHaveClass(PerCourseOrder order, String updater,
            String remark) {
        Map<String, String> map = sysConfigBO.querySYSConfigMap("4");
        // 类型（0、私教订单。1、达人订单）
        Long penalty = 0L;
        String title = "教练";
        EBizType bizType = EBizType.BWYYHFC;
        if (EBoolean.NO.getCode().equals(order.getType())) {
            title = "教练";
            bizType = EBizType.BWYYHFC;
            // 计算违约金额
            penalty = AmountUtil.mul(order.getAmount(), StringValidater
                .toDouble(map.get(ESysConfigCkey.SBWY.getCode())));
        } else {
            title = "达人";
            bizType = EBizType.QWYYHFC;
            // 计算违约金额
            penalty = AmountUtil.mul(order.getAmount(), StringValidater
                .toDouble(map.get(ESysConfigCkey.SMWY.getCode())));

        }
        order.setPreStatus(order.getStatus());
        order.setCoachPenalty(penalty);
        order.setStatus(EPerCourseOrderStatus.PLAT_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.platCancelOrder(order);
        // 平台会收到多少教练违约金
        accountBO.doTransferAmountRemote(order.getToUser(),
            ESysUser.SYS_USER_ZWZJ.getCode(), ECurrency.CNY, penalty, bizType,
            "上课后" + title + "违约,扣除违约金额", "上课后" + title + "违约,获得违约金额",
            order.getCode());
        this.private10(order, map, penalty);// 用户分成
        this.private11(order, map, penalty);// 推荐分成
    }

    private void private11(PerCourseOrder order, Map<String, String> map,
            Long penalty) {
        // 类型（0、私教订单。1、达人订单）
        Long tjWyAmount = 0L;
        User user = userBO.getRemoteUser(order.getApplyUser());
        if (EBoolean.NO.getCode().equals(order.getType())) {
            if (StringUtils.isNotEmpty(user.getUserReferee())) {
                // 上课后教练违约,推荐人获得多少钱
                tjWyAmount = AmountUtil.mul(penalty, StringValidater
                    .toDouble(map.get(ESysConfigCkey.BQ.getCode())));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), user.getUserReferee(),
                    ECurrency.CNY, tjWyAmount, EBizType.TJ, "上课后教练违约,获得推荐分成",
                    "上课后教练违约,获得推荐分成", order.getCode());
                smsOutBO.sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                        + order.getCode() + "]在上课后被教练取消，您收到推荐分成"
                        + CalculationUtil.divi(tjWyAmount) + "元，登录网站可查看详情。");
            }
        } else {
            if (StringUtils.isNotEmpty(user.getUserReferee())) {
                // 上课后达人违约,推荐人获得多少钱
                tjWyAmount = AmountUtil.mul(penalty, StringValidater
                    .toDouble(map.get(ESysConfigCkey.MLXSQ.getCode())));
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_ZWZJ.getCode(), user.getUserReferee(),
                    ECurrency.CNY, tjWyAmount, EBizType.TJ, "上课后达人违约,获得推荐分成",
                    "上课后达人违约,获得推荐分成", order.getCode());
                smsOutBO.sentContent(user.getUserReferee(), "尊敬的用户,由于订单：["
                        + order.getCode() + "]在上课后被达人取消，您收到推荐分成"
                        + CalculationUtil.divi(tjWyAmount) + "元，登录网站可查看详情。");
            }
        }
    }

    private void private10(PerCourseOrder order, Map<String, String> map,
            Long penalty) {
        // 类型（0、私教订单。1、达人订单）
        Long penaltyAmount = 0L;
        if (EBoolean.NO.getCode().equals(order.getType())) {
            // 用户除了退款还会获得多少违约金
            penaltyAmount = AmountUtil.mul(penalty, StringValidater
                .toDouble(map.get(ESysConfigCkey.SBWYYHFC.getCode())));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, penaltyAmount,
                EBizType.BWYYHFCY, "上课后教练违约,获得违约分成", "上课后教练违约,获得违约分成",
                order.getCode());
        } else if (EBoolean.YES.getCode().equals(order.getType())) {
            // 用户除了退款还会获得多少违约金
            penaltyAmount = AmountUtil.mul(penalty, StringValidater
                .toDouble(map.get(ESysConfigCkey.SDWYYHFC.getCode())));
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZWZJ.getCode(),
                order.getApplyUser(), ECurrency.CNY, penaltyAmount,
                EBizType.QWYYHFCY, "上课后达人违约,获得分成", "上课后达人违约,获得分成",
                order.getCode());
        }
    }
}
