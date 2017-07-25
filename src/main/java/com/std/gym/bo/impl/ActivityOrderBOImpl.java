package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IActivityOrderBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IActivityOrderDAO;
import com.std.gym.domain.ActivityOrder;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.exception.BizException;

/**
 * 
 * @author: shan 
 * @since: 2016年12月12日 下午12:46:10 
 * @history:
 */
@Component
public class ActivityOrderBOImpl extends PaginableBOImpl<ActivityOrder>
        implements IActivityOrderBO {
    @Autowired
    IActivityOrderDAO activityOrderDAO;

    @Override
    public boolean isOrderExist(String code) {
        ActivityOrder condition = new ActivityOrder();
        condition.setCode(code);
        if (activityOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveOrder(ActivityOrder data) {
        activityOrderDAO.insert(data);
    }

    @Override
    public ActivityOrder getActivityOrder(String code) {
        ActivityOrder order = null;
        if (StringUtils.isNotBlank(code)) {
            ActivityOrder data = new ActivityOrder();
            data.setCode(code);
            order = activityOrderDAO.select(data);
            if (order == null) {
                throw new BizException("xn0000", "活动订单不存在");
            }
        }
        return order;
    }

    @Override
    public List<ActivityOrder> queryOrderList(ActivityOrder data) {
        return activityOrderDAO.selectList(data);
    }

    @Override
    public int refreshPay(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ActivityOrder data = new ActivityOrder();
            data.setCode(code);
            data.setStatus(EActivityOrderStatus.PAYSUCCESS.getCode());
            data.setPayDatetime(new Date());
            count = activityOrderDAO.updateOrderPay(data);
        }
        return count;
    }

    @Override
    public ActivityOrder getOrderPayGroup(String payGroup) {
        ActivityOrder order = null;
        if (StringUtils.isNotBlank(payGroup)) {
            ActivityOrder data = new ActivityOrder();
            data.setPayGroup(payGroup);
            order = activityOrderDAO.selectGroup(data);
            if (order == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return order;
    }

    @Override
    public void payGroup(ActivityOrder order, String payGroup) {
        order.setPayGroup(payGroup);
        activityOrderDAO.payGroup(order);
    }

    @Override
    public void paySuccess(ActivityOrder order, String payCode, Long amount,
            Long penalty, String payType) {
        order.setStatus(EActivityOrderStatus.PAYSUCCESS.getCode());
        order.setPayType(payType);
        order.setPenalty(penalty);
        order.setPayCode(payCode);
        order.setPayAmount(amount);
        order.setPayDatetime(new Date());
        activityOrderDAO.updateOrderPay(order);
    }

    @Override
    public void userCancel(ActivityOrder order, String updater) {
        order.setStatus(EActivityOrderStatus.USER_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        activityOrderDAO.cancelOrder(order);
    }

    @Override
    public void platCancel(ActivityOrder order, String updater, String remark) {
        order.setStatus(EActivityOrderStatus.PLAT_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        activityOrderDAO.cancelOrder(order);
    }

    @Override
    public void applyRefund(ActivityOrder order, String applyUser,
            String applyNote) {
        order.setStatus(EActivityOrderStatus.APPLY_REFUND.getCode());
        order.setApplyUser(applyUser);
        order.setApplyDatetime(new Date());
        order.setApplyNote(applyNote);
        activityOrderDAO.applyRefund(order);
    }

    @Override
    public void approveRefund(ActivityOrder order, EActivityOrderStatus status,
            String updater, String remark) {
        order.setStatus(status.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        activityOrderDAO.cancelOrder(order);
    }

    @Override
    public void finishOrder(ActivityOrder order) {
        order.setStatus(EActivityOrderStatus.END.getCode());
        activityOrderDAO.finishOrder(order);
    }

    @Override
    public List<ActivityOrder> queryOrderList(String activityCode,
            List<String> statusList) {
        ActivityOrder condition = new ActivityOrder();
        condition.setActivityCode(activityCode);
        condition.setStatusList(statusList);
        return activityOrderDAO.selectList(condition);
    }

    @Override
    public void beginOrder(ActivityOrder activityOrder) {
        activityOrder.setStatus(EActivityOrderStatus.BEGIN.getCode());
        activityOrderDAO.finishOrder(activityOrder);
    }

}
