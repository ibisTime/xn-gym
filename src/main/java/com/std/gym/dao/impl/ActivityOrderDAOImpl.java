package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IActivityOrderDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.ActivityOrder;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月10日 上午9:54:14 
 * @history:
 */
@Repository("activityOrderDAOImpl")
public class ActivityOrderDAOImpl extends AMybatisTemplate implements
        IActivityOrderDAO {

    @Override
    public int insert(ActivityOrder data) {
        return super.insert(NAMESPACE.concat("insert_activityOrder"), data);
    }

    @Override
    public int delete(ActivityOrder data) {
        return super.delete(NAMESPACE.concat("delete_activityOrder"), data);
    }

    @Override
    public ActivityOrder select(ActivityOrder condition) {
        return super.select(NAMESPACE.concat("select_activityOrder"),
            condition, ActivityOrder.class);
    }

    @Override
    public Long selectTotalCount(ActivityOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_activityOrder_count"), condition);
    }

    @Override
    public List<ActivityOrder> selectList(ActivityOrder condition) {
        return super.selectList(NAMESPACE.concat("select_activityOrder"),
            condition, ActivityOrder.class);
    }

    @Override
    public List<ActivityOrder> selectList(ActivityOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_activityOrder"),
            start, count, condition, ActivityOrder.class);
    }

    @Override
    public int update(ActivityOrder data) {
        return super.update(NAMESPACE.concat("update_activityOrder"), data);
    }

    @Override
    public int updateOrderPay(ActivityOrder data) {
        return super.update(NAMESPACE.concat("update_orderPay"), data);
    }

    @Override
    public ActivityOrder selectGroup(ActivityOrder condition) {
        return super.select(NAMESPACE.concat("select_activityOrder"),
            condition, ActivityOrder.class);
    }

    @Override
    public int finishOrder(ActivityOrder data) {
        return super.update(NAMESPACE.concat("update_auto"), data);
    }

    @Override
    public int payGroup(ActivityOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int cancelOrder(ActivityOrder data) {
        return super.update(NAMESPACE.concat("update_cancel"), data);
    }

    @Override
    public int applyRefund(ActivityOrder data) {
        return super.update(NAMESPACE.concat("update_apply_refund"), data);
    }

}
