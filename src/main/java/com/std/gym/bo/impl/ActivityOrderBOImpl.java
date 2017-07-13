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
    IActivityOrderDAO orderDAO;

    @Override
    public boolean isOrderExist(String code) {
        ActivityOrder condition = new ActivityOrder();
        condition.setCode(code);
        if (orderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveOrder(ActivityOrder data) {
        orderDAO.insert(data);
    }

    @Override
    public ActivityOrder getOrder(String code) {
        ActivityOrder order = null;
        if (StringUtils.isNotBlank(code)) {
            ActivityOrder data = new ActivityOrder();
            data.setCode(code);
            order = orderDAO.select(data);
            if (order == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return order;
    }

    @Override
    public List<ActivityOrder> queryOrderList(ActivityOrder data) {
        return orderDAO.selectList(data);
    }

    @Override
    public List<ActivityOrder> queryOrderList(String userId,
            String productCode, List<String> statusList) {
        ActivityOrder data = new ActivityOrder();
        data.setApplyUser(userId);
        data.setActivityCode(productCode);
        data.setStatusList(statusList);
        return orderDAO.selectList(data);
    }

    @Override
    public int refreshPay(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ActivityOrder data = new ActivityOrder();
            data.setCode(code);
            data.setStatus(EActivityOrderStatus.PAYSUCCESS.getCode());
            data.setPayDatetime(new Date());
            count = orderDAO.updateOrderPay(data);
        }
        return count;
    }

    @Override
    public ActivityOrder getOrderPayGroup(String payGroup) {
        ActivityOrder order = null;
        if (StringUtils.isNotBlank(payGroup)) {
            ActivityOrder data = new ActivityOrder();
            data.setPayGroup(payGroup);
            order = orderDAO.selectGroup(data);
            if (order == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return order;
    }

    @Override
    public void payGroup(ActivityOrder order, String payGroup) {
        order.setPayGroup(payGroup);
        orderDAO.payGroup(order);
    }

    @Override
    public void paySuccess(ActivityOrder order, String payCode, Long amount) {
        order.setStatus(EActivityOrderStatus.PAYSUCCESS.getCode());
        order.setPayCode(payCode);
        order.setPayAmount(amount);
        order.setPayDatetime(new Date());
        orderDAO.updateOrderPay(order);
    }

    @Override
    public void refreshCancelOrder(ActivityOrder order, String updater,
            String remark) {
        order.setStatus(EActivityOrderStatus.CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.cancelOrder(order);
    }

    @Override
    public void auto(ActivityOrder order) {
        order.setStatus(EActivityOrderStatus.END.getCode());
        orderDAO.auto(order);
    }

}
