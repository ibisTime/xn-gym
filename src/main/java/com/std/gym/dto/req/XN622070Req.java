package com.std.gym.dto.req;

/**
 * 团课报名
 * @author: asus 
 * @since: 2017年7月17日 下午7:51:58 
 * @history:
 */
public class XN622070Req {
    // 团课编号
    private String orgCourseCode;

    // 申请人
    private String applyUser;

    // 申请备注
    private String applyNote;

    // 手机号
    private String mobile;

    // 数量
    private String quantity;

    public String getOrgCourseCode() {
        return orgCourseCode;
    }

    public void setOrgCourseCode(String orgCourseCode) {
        this.orgCourseCode = orgCourseCode;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
