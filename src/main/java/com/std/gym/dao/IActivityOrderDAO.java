package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.ActivityOrder;

/**
 * 
 * @author: shan 
 * @since: 2016年12月9日 下午9:40:38 
 * @history:
 */
public interface IActivityOrderDAO extends IBaseDAO<ActivityOrder> {
    String NAMESPACE = IActivityOrderDAO.class.getName().concat(".");

    public int update(ActivityOrder data);

    public int payGroup(ActivityOrder order);

    public int updateOrderPay(ActivityOrder data);

    public int auto(ActivityOrder data);

    public ActivityOrder selectGroup(ActivityOrder data);

    public int cancelOrder(ActivityOrder order);

}
