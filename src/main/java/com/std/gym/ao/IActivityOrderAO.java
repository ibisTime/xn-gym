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
     * @param data
     * @return 
     * @create: 2016年12月10日 下午2:51:06 shan
     * @history:
     */
    public String addOrder(String applyUser, String productCode,
            String realName, String mobile, Integer quantity);

    /**
     * 分页查询订单
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年12月10日 下午2:51:16 shan
     * @history:
     */
    public Paginable<ActivityOrder> queryOrderPage(int start, int limit,
            ActivityOrder condition);

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
    public ActivityOrder getOrder(String code);

    /**
     * 支付订单
     * @param code 
     * @create: 2016年12月15日 下午5:04:23 shan
     * @history:
     */
    public Object orderPay(String orderCode, String payType);

    public void paySuccess(String payGroup, String payCode, Long amount);

    public void changeOrder();

    public void cancelOrder(String orderCode, String updater, String remark);

}
