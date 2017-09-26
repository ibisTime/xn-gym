package com.std.gym.dto.req;

import java.util.List;

/**
 * 分页查询参与
 * @author: asus 
 * @since: 2017年9月7日 下午5:29:54 
 * @history:
 */
public class XN622230Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1326750888848669327L;

    // 教练编号
    private String coachCode;

    // 编号
    private String code;

    // 真实姓名
    private String realName;

    // 类型
    private String type;

    // 当前用户
    private String userId;

    // 教练状态
    private String coachStatus;

    // 教练状态
    private List<String> coachStatusList;

    // 活动状态
    private String activityStatus;

    // 活动编号
    private String activityCode;

    private String title;

    public String getCoachCode() {
        return coachCode;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoachStatus() {
        return coachStatus;
    }

    public void setCoachStatus(String coachStatus) {
        this.coachStatus = coachStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public List<String> getCoachStatusList() {
        return coachStatusList;
    }

    public void setCoachStatusList(List<String> coachStatusList) {
        this.coachStatusList = coachStatusList;
    }
}
