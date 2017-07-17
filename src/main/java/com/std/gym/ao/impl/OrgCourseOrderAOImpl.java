package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.domain.Account;
import com.std.gym.domain.OrgCourse;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.domain.User;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.ECurrency;
import com.std.gym.enums.EPayType;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.ESysAccount;
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
    private IAccountBO accountBO;

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
            ESysAccount.SYS_USER_ZWZJ.getCode(), ECurrency.CNY,
            order.getAmount(), EBizType.AJ_TKGM, EBizType.AJ_TKGM.getValue(),
            EBizType.AJ_TKGM.getValue(), order.getCode());
        paySuccess(payGroup, null, order.getAmount(), EPayType.YE.getCode());
        return new BooleanRes(true);
    }

    public Object toPayWEIXIH5(OrgCourseOrder order, User user,
            String payGroup, OrgCourse orgCourse) {
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), ESysAccount.SYS_USER_ZWZJ.getCode(), payGroup,
            order.getCode(), EBizType.AJ_TKGM, EBizType.AJ_TKGM.getValue(),
            order.getAmount(), EBizType.AJ_TKGM.getValue());
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType) {
        OrgCourseOrder order = orgCourseOrderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orgCourseOrderBO.paySuccess(order, payCode, amount, payType);
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
    public void editOrgCourseOrder(OrgCourseOrder data) {
        if (!orgCourseOrderBO.isOrgCourseOrderExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        orgCourseOrderBO.refreshOrgCourseOrder(data);
    }

    @Override
    public Paginable<OrgCourseOrder> queryOrgCourseOrderPage(int start,
            int limit, OrgCourseOrder condition) {
        return orgCourseOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition) {
        return orgCourseOrderBO.queryOrgCourseOrderList(condition);
    }

    @Override
    public OrgCourseOrder getOrgCourseOrder(String code) {
        return orgCourseOrderBO.getOrgCourseOrder(code);
    }

}
