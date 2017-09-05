package com.std.gym.dto.req;

import java.util.List;

/**
 * 分页查询私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午8:27:34 
 * @history:
 */
public class XN622130Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2065073473356586943L;

    // 下单人
    private String applyUser;

    // 课程名称
    private String courseName;

    // 手机号
    private String mobile;

    // 教练编号
    private String toUser;

    // 私课编号
    private String perCourseCode;

    // 状态
    private String status;

    // 状态
    private List<String> statusList;

    // 更新人
    private String updater;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getPerCourseCode() {
        return perCourseCode;
    }

    public void setPerCourseCode(String perCourseCode) {
        this.perCourseCode = perCourseCode;
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
