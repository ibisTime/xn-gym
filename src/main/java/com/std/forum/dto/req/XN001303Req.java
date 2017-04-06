package com.std.forum.dto.req;

public class XN001303Req {
    // 用户编号（必填）
    private String userId;

    // 币种类型（必填）
    private String currency;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
