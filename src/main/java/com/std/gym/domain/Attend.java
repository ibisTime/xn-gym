package com.std.gym.domain;

import java.util.Date;

import com.std.gym.dao.base.ABaseDO;

/**
* 参与表
* @author: shan 
* @since: 2017-09-07 11:49:50
* @history:
*/
public class Attend extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private String code;

    // 类型
    private String type;

    // 用户编号
    private String userId;

    // 教练/达人编号
    private String coachCode;

    // 活动编号
    private String activityCode;

    // 编号
    private Integer orderNo;

    // 参加时间
    private Date jionDatetime;

    // 活动开始时间
    private Date startDatetime;

    // 活动结束时间
    private Date endDatetime;

    // 投票数
    private Integer totalNum;

    // -----------------------
    // 真实姓名
    private String realName;

    // 标题
    private String title;

    // 教练
    private Coach coach;

    // 教练状态
    private String coachStatus;

    // 活动状态
    private String activityStatus;

    // 是否投票
    private String isVote;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setJionDatetime(Date jionDatetime) {
        this.jionDatetime = jionDatetime;
    }

    public Date getJionDatetime() {
        return jionDatetime;
    }

    public String getCoachCode() {
        return coachCode;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getIsVote() {
        return isVote;
    }

    public void setIsVote(String isVote) {
        this.isVote = isVote;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getCoachStatus() {
        return coachStatus;
    }

    public void setCoachStatus(String coachStatus) {
        this.coachStatus = coachStatus;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

}
