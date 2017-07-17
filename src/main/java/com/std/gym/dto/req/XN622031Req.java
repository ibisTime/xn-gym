package com.std.gym.dto.req;

/**
 * 支付活动订单
 * @author: asus 
 * @since: 2017年7月17日 下午1:49:13 
 * @history:
 */
public class XN622031Req {
    // 订单编号
    private String orderCode;

    // 支付方式
    private String payType;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
