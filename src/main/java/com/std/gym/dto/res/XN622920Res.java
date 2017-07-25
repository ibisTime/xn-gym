package com.std.gym.dto.res;

/**
 * 统计所有未完成的订单
 * @author: asus 
 * @since: 2017年7月25日 下午4:20:52 
 * @history:
 */
public class XN622920Res {
    // 活动订单数量
    private Long activityCount;

    // 团课订单数量
    private Long orgCourseCount;

    // 私课订单数量
    private Long perCourseCount;

    public Long getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Long activityCount) {
        this.activityCount = activityCount;
    }

    public Long getOrgCourseCount() {
        return orgCourseCount;
    }

    public void setOrgCourseCount(Long orgCourseCount) {
        this.orgCourseCount = orgCourseCount;
    }

    public Long getPerCourseCount() {
        return perCourseCount;
    }

    public void setPerCourseCount(Long perCourseCount) {
        this.perCourseCount = perCourseCount;
    }
}
