package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Activity;

/**
 * 活动
 * @author: shan 
 * @since: 2016年12月10日 下午2:55:32 
 * @history:
 */
public interface IActivityBO extends IPaginableBO<Activity> {

    public boolean isActivityExist(String code);

    /**
     * 添加活动
     * @param data
     * @return 
     * @create: 2016年12月10日 下午3:11:09 shan
     * @history:
     */
    public void saveActivity(Activity data);

    /**
     * 删除活动
     * @param code
     * @return 
     * @create: 2016年12月10日 下午3:11:12 shan
     * @history:
     */
    public int deleteActivity(String code);

    /**
     * 活动审核更新
     * @param data
     * @return 
     * @create: 2016年12月10日 下午3:11:15 shan
     * @history:
     */
    public int refreshActivity(Activity data);

    /**
     * 活动更新
     * @param data
     * @return 
     * @create: 2016年12月27日 下午5:58:03 asus
     * @history:
     */
    public void modifyActivity(Activity data);

    /**
     * 活动上架
     * @param activity
     * @param updater
     * @param remark 
     * @param location 
     * @param orderNo 
     * @create: 2017年4月20日 下午2:46:48 asus
     * @history:
     */
    public void putOn(Activity activity, String location, String orderNo,
            String updater, String remark);

    /**
     * 活动下架
     * @param activity
     * @param updater
     * @param remark 
     * @create: 2017年4月20日 下午2:46:48 asus
     * @history:
     */
    public void downActivity(Activity activity, String updater, String remark);

    /**
     * 活动截止报名
     * @param activity
     * @param updater
     * @param remark 
     * @create: 2017年7月17日 上午10:52:17 asus
     * @history:
     */
    public void stopActivity(Activity activity, String updater, String remark);

    /**
     * 活动报名数
     * @param activity
     * @param signNum
     * @return 
     * @create: 2016年12月15日 下午7:59:33 shan
     * @history:
     */
    public void addSignNum(Activity activity, Integer quantity);

    /**
     * 获取活动详情
     * @param code
     * @return 
     * @create: 2016年12月10日 下午3:11:18 shan
     * @history:
     */
    public Activity getActivity(String code);

    /**
     * 查询所有活动
     * @param data
     * @return 
     * @create: 2016年12月10日 下午3:11:21 shan
     * @history:
     */
    public List<Activity> queryActivityList(Activity data);

    public void auto(Activity activity);

}
