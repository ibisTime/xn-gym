package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IActivityDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.Activity;

/**
 * 活动
 * @author: shan 
 * @since: 2016年12月10日 上午9:16:56 
 * @history:
 */
@Repository("activityDAOImpl")
public class ActivityDAOImpl extends AMybatisTemplate implements IActivityDAO {
    /**
     * 
     * @see com.cdkj.ride.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Activity data) {
        return super.insert(NAMESPACE.concat("insert_activity"), data);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Activity data) {
        return super.delete(NAMESPACE.concat("delete_activity"), data);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Activity select(Activity condition) {
        return super.select(NAMESPACE.concat("select_activity"), condition,
            Activity.class);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public Long selectTotalCount(Activity condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_activity_count"), condition);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Activity> selectList(Activity condition) {
        return super.selectList(NAMESPACE.concat("select_activity"), condition,
            Activity.class);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Activity> selectList(Activity condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_activity"), start,
            count, condition, Activity.class);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.IActivityDAO#update(com.cdkj.ride.domain.Activity)
     */
    @Override
    public int update(Activity data) {
        return super.update(NAMESPACE.concat("update_activity"), data);
    }

    @Override
    public int shelves(Activity data) {
        return super.update(NAMESPACE.concat("update_shelves"), data);
    }

    @Override
    public int downActivity(Activity data) {
        return super.update(NAMESPACE.concat("update_down"), data);
    }

    @Override
    public int auto(Activity data) {
        return super.update(NAMESPACE.concat("update_auto"), data);
    }

    @Override
    public int scanActivity(Activity data) {
        return super.update(NAMESPACE.concat("update_scan"), data);
    }

    @Override
    public int addSignNum(Activity data) {
        return super.update(NAMESPACE.concat("update_sign"), data);
    }
}
