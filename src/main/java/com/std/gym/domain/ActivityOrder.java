package com.std.gym.domain;

import java.util.Date;
import java.util.List;

import com.std.gym.dao.base.ABaseDO;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月9日 下午9:25:39 
 * @history:
 */
public class ActivityOrder extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 订单编号
    private String code;

    // 活动编号
    private String activityCode;

    // 活动名称
    private String activityTitle;

    // 报名人数
    private Integer quantity;

    // 单价
    private Long price;

    // 订单总额
    private Long amount;

    // 订单状态
    private String status;

    // 支付组号
    private String payType;

    // 支付组号
    private String payGroup;

    // 支付编号
    private String payCode;

    // 支付金额
    private Long payAmount;

    // 用户支付时间
    private Date payDatetime;

    // 更新人
    private String applyUser;

    // 联系方式
    private String mobile;

    // 更新时间
    private Date applyDatetime;

    // 备注
    private String applyNote;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // *************DB**************
    // 创建开始时间
    private Date createBeginDatetime;

    // 创建结束时间
    private Date createEndDatetime;

    // 支付开始时间
    private Date payBeginDatetime;

    // 支付结束时间
    private Date payEndDatetime;

    // 活动开始时间
    private Date activityBeginDatetime;

    // 活动结束时间
    private Date activityEndDatetime;

    // 活动图片
    private String pic;

    // 用户名
    private String nickname;

    // 状态List
    private List<String> statusList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
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

    public Date getCreateBeginDatetime() {
        return createBeginDatetime;
    }

    public void setCreateBeginDatetime(Date createBeginDatetime) {
        this.createBeginDatetime = createBeginDatetime;
    }

    public Date getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(Date createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

    public Date getPayBeginDatetime() {
        return payBeginDatetime;
    }

    public void setPayBeginDatetime(Date payBeginDatetime) {
        this.payBeginDatetime = payBeginDatetime;
    }

    public Date getPayEndDatetime() {
        return payEndDatetime;
    }

    public void setPayEndDatetime(Date payEndDatetime) {
        this.payEndDatetime = payEndDatetime;
    }

    public Date getActivityBeginDatetime() {
        return activityBeginDatetime;
    }

    public void setActivityBeginDatetime(Date activityBeginDatetime) {
        this.activityBeginDatetime = activityBeginDatetime;
    }

    public Date getActivityEndDatetime() {
        return activityEndDatetime;
    }

    public void setActivityEndDatetime(Date activityEndDatetime) {
        this.activityEndDatetime = activityEndDatetime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
