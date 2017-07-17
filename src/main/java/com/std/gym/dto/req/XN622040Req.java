package com.std.gym.dto.req;

/**
 * 分页查询订单
 * @author: asus 
 * @since: 2017年7月17日 下午3:37:09 
 * @history:
 */
public class XN622040Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8482767987923651576L;

    // 申请人
    private String applyUser;

    // 手机号
    private String mobile;

    // 活动编号
    private String activityCode;

    // 活动标题
    private String activityTitle;

    // 申请开始时间
    private String applyBeginDatetime;

    // 申请结束时间
    private String applyEndDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getApplyBeginDatetime() {
        return applyBeginDatetime;
    }

    public void setApplyBeginDatetime(String applyBeginDatetime) {
        this.applyBeginDatetime = applyBeginDatetime;
    }

    public String getApplyEndDatetime() {
        return applyEndDatetime;
    }

    public void setApplyEndDatetime(String applyEndDatetime) {
        this.applyEndDatetime = applyEndDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
