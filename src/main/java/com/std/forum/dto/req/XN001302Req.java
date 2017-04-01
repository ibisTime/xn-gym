package com.std.forum.dto.req;

public class XN001302Req {
    // 必填（必填）
    private String userId;

    // 选填（必填）
    private String level;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
