package com.std.gym.ao.impl;

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
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.domain.Activity;
import com.std.gym.domain.ActivityOrder;
import com.std.gym.domain.User;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EPayType;
import com.std.gym.enums.EPrefixCode;
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
    IActivityOrderBO orderBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IActivityBO activityBO;

    /**
     * 新增订单
     * @see com.cdkj.ride.ao.IOrderAO#addOrder(com.ActivityOrder.ride.domain.Order)
     */
    @Override
    public String addOrder(String applyUser, String productCode,
            String realName, String mobile, Integer quantity) {
        Activity activity = activityBO.getActivity(productCode);
        this.checkActivity(activity, quantity);

        ActivityOrder data = new ActivityOrder();
        String code = OrderNoGenerater.generate(EPrefixCode.ActivityOrder
            .getCode());
        data.setCode(code);
        data.setApplyUser(applyUser);
        data.setActivityCode(productCode);
        data.setActivityTitle(activity.getTitle());
        data.setMobile(mobile);
        data.setApplyDatetime(new Date());
        data.setQuantity(quantity);
        data.setPrice(activity.getAmount());
        data.setAmount(quantity * activity.getAmount());
        data.setStatus(EActivityOrderStatus.NOTPAY.getCode());
        orderBO.saveOrder(data);
        return code;
    }

    private void checkActivity(Activity activity, Integer quantity) {
        if (EActivityStatus.DRAFT.getCode().equals(activity.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(
                    activity.getStatus())
                || EActivityStatus.END.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "活动不在可下单范围内");
        }
        if (activity.getStartDatetime().before(new Date())) {
            throw new BizException("xn0000", "活动已经截止报名");
        }
    }

    /**
     * 分页查询订单
     * @see com.cdkj.ride.ao.IOrderAO#queryOrderPage(int, int, com.ActivityOrder.ride.domain.Order)
     */
    @Override
    public Paginable<ActivityOrder> queryOrderPage(int start, int limit,
            ActivityOrder condition) {
        Paginable<ActivityOrder> page = orderBO.getPaginable(start, limit,
            condition);
        List<ActivityOrder> orderList = page.getList();
        for (ActivityOrder order : orderList) {
            Activity activity = activityBO.getActivity(order.getActivityCode());
            order.setActivityBeginDatetime(activity.getStartDatetime());
            order.setActivityEndDatetime(activity.getEndDatetime());
            order.setPic(activity.getPic());
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
        List<ActivityOrder> orderList = orderBO.queryOrderList(condition);
        for (ActivityOrder order : orderList) {
            Activity activity = activityBO.getActivity(order.getActivityCode());
            order.setActivityBeginDatetime(activity.getStartDatetime());
            order.setActivityEndDatetime(activity.getEndDatetime());
            order.setPic(activity.getPic());
            User user = userBO.getRemoteUser(order.getApplyUser());
            order.setNickname(user.getNickname());
        }
        return orderList;
    }

    /**
     * 查询订单详情
     * @see com.cdkj.ride.ao.IOrderAO#getOrder(java.lang.String)
     */
    @Override
    public ActivityOrder getOrder(String code) {
        return orderBO.getOrder(code);
    }

    @Override
    @Transactional
    public Object orderPay(String orderCode, String payType) {
        Object result = null;
        ActivityOrder order = orderBO.getOrder(orderCode);
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
        String payGroup = OrderNoGenerater.generate(EPrefixCode.ActivityOrder
            .getCode());
        orderBO.payGroup(order, payGroup);
        if (order.getAmount() == 0L) {
            paySuccess(payGroup, null, order.getAmount());
            return new BooleanRes(true);
        }
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayWEIXIH5(order, user, payGroup, activity);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;

    }

    public Object toPayWEIXIH5(ActivityOrder order, User user, String payGroup,
            Activity activity) {
        if (StringUtils.isBlank(user.getOpenId())) {
            throw new BizException("xn000000", "请先绑定微信");
        }
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), user.getUserId(), order.getAmount(),
            EBizType.AJ_GW, EBizType.AJ_GW.getValue(),
            EBizType.AJ_GW.getValue(), payGroup);
    }

    @Override
    public void changeOrder() {
        logger.info("***************开始扫描待支付订单，活动结束制完成状态***************");
        ActivityOrder condition = new ActivityOrder();
        condition.setStatus(EActivityOrderStatus.PAYSUCCESS.getCode());
        List<ActivityOrder> orderList = orderBO.queryOrderList(condition);
        if (orderList != null && orderList.size() > 0) {
            for (ActivityOrder order : orderList) {
                Activity activity = activityBO.getActivity(order
                    .getActivityCode());
                if (activity.getEndDatetime().before(new Date())) {
                    orderBO.auto(order);
                }
            }
        }
        logger.info("***************开始扫描待支付订单，活动结束制完成状态***************");
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount) {
        ActivityOrder order = orderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orderBO.paySuccess(order, payCode, amount);
            Activity activity = activityBO.getActivity(order.getActivityCode());
        } else {
            logger.info("订单号：" + order.getCode() + "，已成功支付,无需重复支付");
        }

    }

    @Override
    public void cancelOrder(String orderCode, String updater, String remark) {
        ActivityOrder order = orderBO.getOrder(orderCode);
        Activity activity = activityBO.getActivity(order.getActivityCode());
        if (EActivityOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orderBO.refreshCancelOrder(order, updater, remark);
        } else if (EActivityOrderStatus.PAYSUCCESS.getCode().equals(
            order.getStatus())) {
            orderBO.refreshCancelOrder(order, updater, remark);
            if (activity.getStartDatetime().after(new Date())) {
            }
        }
    }
}
