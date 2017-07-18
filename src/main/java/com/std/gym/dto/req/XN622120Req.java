package com.std.gym.dto.req;

/**
 * 提交私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午5:13:32 
 * @history:
 */
public class XN622120Req {
    // 申请人
    private String applyUser;

    // 地址
    private String address;

    // 手机号
    private String mobile;

    // 私课编号
    private String perCourseCode;

    // 人数
    private String quantity;

    // 申请备注
    private String applyNote;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPerCourseCode() {
        return perCourseCode;
    }

    public void setPerCourseCode(String perCourseCode) {
        this.perCourseCode = perCourseCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }
}
