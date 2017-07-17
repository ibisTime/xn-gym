package com.std.gym.ao;

import java.util.List;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Activity;
import com.std.gym.dto.req.XN622010Req;
import com.std.gym.dto.req.XN622012Req;

/**
 * 活动
 * @author: shan 
 * @since: 2016年12月12日 上午9:30:26 
 * @history:
 */
public interface IActivityAO {
    static String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增活动
     * @param req
     * @return 
     * @create: 2016年12月12日 上午9:32:43 shan
     * @history:
     */
    public String addNewActivity(XN622010Req req);

    /**
     * 删除活动
     * @param code 
     * @create: 2017年7月17日 上午10:01:45 asus
     * @history:
     */
    public void dropActivity(String code);

    /**
     * 活动修改
     * @param req 
     * @create: 2017年4月20日 下午2:11:59 asus
     * @history:
     */
    public void modifyActivity(XN622012Req req);

    /**
     * 活动上架
     * @param code
     * @param location
     * @param orderNo
     * @param updater
     * @param remark 
     * @create: 2017年7月17日 上午10:36:57 asus
     * @history:
     */
    public void putOn(String code, String location, String orderNo,
            String updater, String remark);

    /**
     * 活动下架
     * @param code
     * @param updater
     * @param remark 
     * @create: 2017年4月20日 下午2:54:53 asus
     * @history:
     */
    public void downActivity(String code, String updater, String remark);

    /**
     * 截止活动
     * @param code
     * @param updater
     * @param remark 
     * @create: 2017年4月20日 下午2:54:53 asus
     * @history:
     */
    public void stopActivity(String code, String updater, String remark);

    /**
     * 分页查询所有活动
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年12月12日 上午9:32:51 shan
     * @history:
     */
    public Paginable<Activity> queryActivityPage(int start, int limit,
            Activity condition);

    /**
     * 查询所有活动
     * @param condition
     * @return 
     * @create: 2016年12月12日 上午9:32:54 shan
     * @history:
     */
    public List<Activity> queryActivityList(Activity condition);

    /**
     * 查询活动详情
     * @param code
     * @return 
     * @create: 2016年12月12日 上午9:32:57 shan
     * @history:
     */
    public Activity getActivity(String code);

    public void changeOrder();

}
