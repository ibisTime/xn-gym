package com.std.gym.domain;

import com.std.gym.dao.base.ABaseDO;

/**
* 私课
* @author: xieyj 
* @since: 2017-07-13 16:38:13
* @history:
*/
public class PerCourse extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 上课周期
	private String skCycle;

	// 上课时间(时)
	private String skStartDatetime;

	// 下课时间(止)
	private String skEndDatetime;

	// 私教user_id
	private String userId;

	// 私教编号
	private String coachCode;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setSkCycle(String skCycle) {
		this.skCycle = skCycle;
	}

	public String getSkCycle() {
		return skCycle;
	}

	public void setSkStartDatetime(String skStartDatetime) {
		this.skStartDatetime = skStartDatetime;
	}

	public String getSkStartDatetime() {
		return skStartDatetime;
	}

	public void setSkEndDatetime(String skEndDatetime) {
		this.skEndDatetime = skEndDatetime;
	}

	public String getSkEndDatetime() {
		return skEndDatetime;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setCoachCode(String coachCode) {
		this.coachCode = coachCode;
	}

	public String getCoachCode() {
		return coachCode;
	}

}