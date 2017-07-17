package com.std.gym.dto.req;

/**
 * 用户取消订单
 * @author: asus 
 * @since: 2017年7月17日 下午2:38:22 
 * @history:
 */
public class XN622032Req {
    // 订单编号
    private String orderCode;

    // 申请人
    private String applyUser;

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
}
