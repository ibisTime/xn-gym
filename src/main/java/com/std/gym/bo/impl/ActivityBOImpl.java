package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IActivityBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.StringValidater;
import com.std.gym.dao.IActivityDAO;
import com.std.gym.domain.Activity;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.exception.BizException;

/**
 * 活动
 * @author: shan 
 * @since: 2016年12月12日 上午11:54:05 
 * @history:
 */
@Component
public class ActivityBOImpl extends PaginableBOImpl<Activity> implements
        IActivityBO {
    @Autowired
    IActivityDAO activityDAO;

    @Override
    public boolean isActivityExist(String code) {
        Activity condition = new Activity();
        condition.setCode(code);
        if (activityDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveActivity(Activity data) {
        activityDAO.insert(data);
    }

    @Override
    public int deleteActivity(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Activity data = new Activity();
            data.setCode(code);
            count = activityDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshActivity(Activity data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = activityDAO.update(data);
        }
        return count;
    }

    @Override
    public Activity getActivity(String code) {
        Activity activity = null;
        if (StringUtils.isNotBlank(code)) {
            Activity data = new Activity();
            data.setCode(code);
            activity = activityDAO.select(data);
            if (activity == null) {
                throw new BizException("xn0000", "活动不存在");
            }
        }
        return activity;
    }

    @Override
    public List<Activity> queryActivityList(Activity data) {
        return activityDAO.selectList(data);
    }

    @Override
    public void modifyActivity(Activity data) {
        activityDAO.update(data);
    }

    @Override
    public void putOn(Activity activity, String location, String orderNo,
            String updater, String remark) {
        activity.setStatus(EActivityStatus.ONLINE.getCode());
        activity.setLocation(location);
        activity.setOrderNo(StringValidater.toInteger(orderNo));
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.putOn(activity);
    }

    @Override
    public void downActivity(Activity activity, String updater, String remark) {
        activity.setStatus(EActivityStatus.OFFLINE.getCode());
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.downActivity(activity);
    }

    @Override
    public void stopActivity(Activity activity, String updater, String remark) {
        activity.setStatus(EActivityStatus.STOP.getCode());
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.downActivity(activity);
    }

    @Override
    public void addSignNum(Activity activity, Integer quantity) {
        activity.setRemainNum(quantity);
        activityDAO.addSignNum(activity);
    }

    @Override
    public void beginActivity(Activity activity, String updater, String remark) {
        activity.setStatus(EActivityStatus.BEGIN.getCode());
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.downActivity(activity);
    }

    @Override
    public void endActivity(Activity activity, String updater, String remark) {
        activity.setStatus(EActivityStatus.END.getCode());
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.downActivity(activity);
    }

}
