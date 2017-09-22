package com.std.gym.domain;

import java.util.Date;
import java.util.List;

import com.std.gym.dao.base.ABaseDO;

/**
* 私课订单
* @author: xieyj 
* @since: 2017-07-13 16:41:31
* @history:
*/
public class PerCourseOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型（0、私教订单。1、达人订单）
    private String type;

    // 归属人
    private String toUser;

    // 私课编号
    private String perCourseCode;

    // 私课名称
    private String courseName;

    // 预约上课时间(年月日)
    private Date appointDatetime;

    // 上课时间
    private String skDatetime;

    // 下课时间
    private String xkDatetime;

    // 上课地点
    private String address;

    // 上课人数
    private Integer quantity;

    // 单价
    private Long price;

    // 订单金额
    private Long amount;

    // 取消前状态
    private String preStatus;

    // 状态
    private String status;

    // 上课时间
    private Date skStartDatetime;

    // 下课时间
    private Date skEndDatetime;

    // 支付方式
    private String payType;

    // 支付金额
    private Long payAmount;

    // 支付组号
    private String payGroup;

    // 支付编号
    private String payCode;

    // 支付时间
    private Date payDatetime;

    // 违约金
    private Long penalty;

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

    // 是否已发短信
    private String isSend;

    // ***********db***********
    // 下单时间（起）
    private Date applyBeginDatetime;

    // 下单时间（止）
    private Date applyEndDatetime;

    // 预约时间（起）
    private Date appointBeginDatetime;

    // 预约时间（止）
    private Date appointEndDatetime;

    // 用户名称
    private String realName;

    // 用户名称
    private Coach coach;

    // 状态List
    private List<String> statusList;

    // 体测数据
    private List<SizeData> sizeDataList;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Date getSkStartDatetime() {
        return skStartDatetime;
    }

    public void setSkStartDatetime(Date skStartDatetime) {
        this.skStartDatetime = skStartDatetime;
    }

    public Date getSkEndDatetime() {
        return skEndDatetime;
    }

    public void setSkEndDatetime(Date skEndDatetime) {
        this.skEndDatetime = skEndDatetime;
    }

    public String getPerCourseCode() {
        return perCourseCode;
    }

    public void setPerCourseCode(String perCourseCode) {
        this.perCourseCode = perCourseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getAppointDatetime() {
        return appointDatetime;
    }

    public void setAppointDatetime(Date appointDatetime) {
        this.appointDatetime = appointDatetime;
    }

    public String getSkDatetime() {
        return skDatetime;
    }

    public void setSkDatetime(String skDatetime) {
        this.skDatetime = skDatetime;
    }

    public String getXkDatetime() {
        return xkDatetime;
    }

    public void setXkDatetime(String xkDatetime) {
        this.xkDatetime = xkDatetime;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Long getPenalty() {
        return penalty;
    }

    public void setPenalty(Long penalty) {
        this.penalty = penalty;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SizeData> getSizeDataList() {
        return sizeDataList;
    }

    public void setSizeDataList(List<SizeData> sizeDataList) {
        this.sizeDataList = sizeDataList;
    }

    public String getPreStatus() {
        return preStatus;
    }

    public void setPreStatus(String preStatus) {
        this.preStatus = preStatus;
    }

    public Date getAppointBeginDatetime() {
        return appointBeginDatetime;
    }

    public void setAppointBeginDatetime(Date appointBeginDatetime) {
        this.appointBeginDatetime = appointBeginDatetime;
    }

    public Date getAppointEndDatetime() {
        return appointEndDatetime;
    }

    public void setAppointEndDatetime(Date appointEndDatetime) {
        this.appointEndDatetime = appointEndDatetime;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

}
