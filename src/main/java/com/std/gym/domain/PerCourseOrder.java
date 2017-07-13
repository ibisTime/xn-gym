package com.std.gym.domain;

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

	// 私教编号
	private String coachUserId;

	// 预约上课时间(年月日)
	private String appointDatetime;

	// 上课时间(几点到几点)
	private String classDatetime;

	// 上课地点
	private String address;

	// 上课人数
	private String quantity;

	// 单价
	private String price;

	// 订单金额
	private String amount;

	// 状态
	private String status;

	// 支付方式
	private String payType;

	// 支付金额
	private String payAmount;

	// 支付组号
	private String payGroup;

	// 支付编号
	private String payCode;

	// 支付时间
	private String payDatetime;

	// 申请人
	private String applyUser;

	// 联系方式
	private String mobile;

	// 申请时间
	private String applyDatetime;

	// 申请备注
	private String applyNote;

	// 更新人
	private String updater;

	// 更新时间
	private String updateDatetime;

	// 备注
	private String remark;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCoachUserId(String coachUserId) {
		this.coachUserId = coachUserId;
	}

	public String getCoachUserId() {
		return coachUserId;
	}

	public void setAppointDatetime(String appointDatetime) {
		this.appointDatetime = appointDatetime;
	}

	public String getAppointDatetime() {
		return appointDatetime;
	}

	public void setClassDatetime(String classDatetime) {
		this.classDatetime = classDatetime;
	}

	public String getClassDatetime() {
		return classDatetime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
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

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayAmount() {
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

	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}

	public String getPayDatetime() {
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

	public void setApplyDatetime(String applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	public String getApplyDatetime() {
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

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}