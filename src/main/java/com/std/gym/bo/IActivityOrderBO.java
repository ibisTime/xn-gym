package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.ActivityOrder;
import com.std.gym.enums.EActivityOrderStatus;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月10日 下午2:45:43 
 * @history:
 */
public interface IActivityOrderBO extends IPaginableBO<ActivityOrder> {
    public boolean isOrderExist(String code);

    /**
     * 新增订单
     * @param data
     * @return 
     * @create: 2016年12月10日 下午2:48:05 shan
     * @history:
     */
    public void saveOrder(ActivityOrder data);

    /**
     * 查询订单详情
     * @param code
     * @return 
     * @create: 2016年12月10日 下午2:48:17 shan
     * @history:
     */
    public ActivityOrder getActivityOrder(String code);

    /**
     * 查询所有订单
     * @param data
     * @return 
     * @create: 2016年12月10日 下午2:48:20 shan
     * @history:
     */
    public List<ActivityOrder> queryOrderList(ActivityOrder data);

    /**
     * 支付订单
     * @param code
     * @return 
     * @create: 2016年12月15日 下午4:54:07 shan
     * @history:
     */
    public int refreshPay(String code);

    public ActivityOrder getOrderPayGroup(String payGroup);

    public void payGroup(ActivityOrder order, String payGroup);

    public void paySuccess(ActivityOrder order, String payCode, Long amount,
            Long penalty, String payType);

    public void userCancel(ActivityOrder order, String updater);

    public void platCancel(ActivityOrder order, String updater, String remark);

    public void applyRefund(ActivityOrder order, String applyUser,
            String applyNote);

    public void approveRefund(ActivityOrder order, EActivityOrderStatus status,
            String updater, String remark);

    public void finishOrder(ActivityOrder order);

    public List<ActivityOrder> queryOrderList(String activityCode,
            List<String> statusList);

    public void beginOrder(ActivityOrder activityOrder);

    public Long getUnfinishCount(List<String> statusList);

}
