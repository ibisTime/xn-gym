package com.std.gym.dto.req;

/**
 * 活动报名
 * @author: asus 
 * @since: 2017年7月17日 下午1:14:54 
 * @history:
 */
public class XN622030Req {
    // 活动编号
    private String activityCode;

    // 数量
    private String quantity;

    // 申请人
    private String applyUser;

    // 手机号
    private String mobile;

    // 申请备注
    private String applyNote;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }
}
