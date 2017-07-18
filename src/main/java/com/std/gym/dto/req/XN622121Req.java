package com.std.gym.dto.req;

/**
 * 支付私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午6:57:10 
 * @history:
 */
public class XN622121Req {
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
