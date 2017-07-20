package com.std.gym.dto.req;

import java.util.List;

/**
 * 分页查询团课订单
 * @author: asus 
 * @since: 2017年7月18日 上午10:25:31 
 * @history:
 */
public class XN622080Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8049695233562116272L;

    // 申请人
    private String applyUser;

    // 手机号
    private String mobile;

    // 团课编号
    private String orgCourseCode;

    // 下单开始时间
    private String applyBeginDatetime;

    // 下单结束时间
    private String applyEndDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 状态List
    private List<String> statusList;

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

    public String getOrgCourseCode() {
        return orgCourseCode;
    }

    public void setOrgCourseCode(String orgCourseCode) {
        this.orgCourseCode = orgCourseCode;
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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }
}
