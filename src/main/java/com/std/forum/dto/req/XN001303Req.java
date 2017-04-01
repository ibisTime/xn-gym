package com.std.forum.dto.req;

public class XN001303Req {
    // 用户编号（必填）
    private String userId;

    // 币种类型（必填）
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
