package com.std.gym.dto.req;

/**
 * 用户申请退款
 * @author: asus 
 * @since: 2017年7月17日 下午3:06:01 
 * @history:
 */
public class XN622034Req {
    // 订单编号
    private String orderCode;

    // 申请人
    private String applyUser;

    // 申请备注
    private String applyNote;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
}
