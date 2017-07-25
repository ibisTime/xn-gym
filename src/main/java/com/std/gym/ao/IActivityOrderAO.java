package com.std.gym.ao;

import java.util.List;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.ActivityOrder;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月10日 下午2:49:46 
 * @history:
 */
public interface IActivityOrderAO {
    static String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增订单
     * @param activityCode
     * @param quantity
     * @param applyUser
     * @param applyNote
     * @param mobile
     * @return 
     * @create: 2017年7月17日 下午1:23:54 asus
     * @history:
     */
    public String addActivityOrder(String activityCode, Integer quantity,
            String applyUser, String applyNote, String mobile);

    /**
     * 分页查询订单
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年12月10日 下午2:51:16 shan
     * @history:
     */
    public Paginable<ActivityOrder> queryActivityOrderPage(int start,
            int limit, ActivityOrder condition);

    /**
     * 查询所有订单
     * @param condition
     * @return 
     * @create: 2016年12月10日 下午2:51:20 shan
     * @history:
     */
    public List<ActivityOrder> queryOrderList(ActivityOrder condition);

    /**
     * 查询订单详情
     * @param code
     * @return 
     * @create: 2016年12月10日 下午2:51:23 shan
     * @history:
     */
    public ActivityOrder getActivityOrder(String code);

    /**
     * 支付订单
     * @param code 
     * @create: 2016年12月15日 下午5:04:23 shan
     * @history:
     */
    public Object orderPay(String orderCode, String payType);

    /**
     * 支付成功
     * @param payGroup
     * @param payCode
     * @param amount 
     * @param payType
     * @create: 2017年7月13日 下午3:48:16 asus
     * @history:
     */
    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType);

    public void changeOrder();

    public void userCancel(String orderCode, String updater);

    /**
     * 平台取消订单
     * @param orderCode
     * @param updater
     * @param remark 
     * @create: 2017年7月17日 下午2:49:41 asus
     * @history:
     */
    public void platCancel(String orderCode, String updater, String remark);

    public void applyRefund(String orderCode, String applyUser, String applyNote);

    public void approveRefund(String orderCode, String result, String updater,
            String remark);

    /**
     * 活动开始
     * @param code
     * @param updater
     * @param remark 
     * @create: 2017年7月25日 下午2:01:59 asus
     * @history:
     */
    public void beginActivity(String code, String updater, String remark);

    /**
     * 活动结束
     * @param code
     * @param updater
     * @param remark 
     * @create: 2017年7月25日 下午2:01:59 asus
     * @history:
     */
    public void endActivity(String code, String updater, String remark);
}
