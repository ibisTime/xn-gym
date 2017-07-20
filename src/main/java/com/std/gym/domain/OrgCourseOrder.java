package com.std.gym.domain;

import java.util.Date;
import java.util.List;

import com.std.gym.dao.base.ABaseDO;

/**
* 团课订单
* @author: xieyj 
* @since: 2017-07-13 16:27:24
* @history:
*/
public class OrgCourseOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 团课编号
    private String orgCourseCode;

    // 团课名称
    private String orgCourseName;

    // 购买数量
    private Integer quantity;

    // 单价
    private Long price;

    // 订单金额
    private Long amount;

    // 状态
    private String status;

    // 支付方式
    private String payType;

    // 支付编号
    private String payCode;

    // 支付组号
    private String payGroup;

    // 支付金额
    private Long payAmount;

    // 支付时间
    private Date payDatetime;

    // 申请人
    private String applyUser;

    // 联系方式
    private String mobile;

    // 申请时间
    private Date applyDatetime;

    // 申请备注
    private String applyNote;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***********db***********
    // 下单时间（起）
    private Date applyBeginDatetime;

    // 下单时间（止）
    private Date applyEndDatetime;

    // 用户名称
    private String applyRealName;

    // 用户名称
    private String coachRealName;

    // 团课
    private OrgCourse orgCourse;

    // 状态List
    private List<String> statusList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgCourseCode() {
        return orgCourseCode;
    }

    public void setOrgCourseCode(String orgCourseCode) {
        this.orgCourseCode = orgCourseCode;
    }

    public String getOrgCourseName() {
        return orgCourseName;
    }

    public void setOrgCourseName(String orgCourseName) {
        this.orgCourseName = orgCourseName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

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

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApplyBeginDatetime() {
        return applyBeginDatetime;
    }

    public void setApplyBeginDatetime(Date applyBeginDatetime) {
        this.applyBeginDatetime = applyBeginDatetime;
    }

    public Date getApplyEndDatetime() {
        return applyEndDatetime;
    }

    public void setApplyEndDatetime(Date applyEndDatetime) {
        this.applyEndDatetime = applyEndDatetime;
    }

    public OrgCourse getOrgCourse() {
        return orgCourse;
    }

    public void setOrgCourse(OrgCourse orgCourse) {
        this.orgCourse = orgCourse;
    }

    public String getApplyRealName() {
        return applyRealName;
    }

    public void setApplyRealName(String applyRealName) {
        this.applyRealName = applyRealName;
    }

    public String getCoachRealName() {
        return coachRealName;
    }

    public void setCoachRealName(String coachRealName) {
        this.coachRealName = coachRealName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
