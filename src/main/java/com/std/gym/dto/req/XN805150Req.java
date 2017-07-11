package com.std.gym.dto.req;

public class XN805150Req {

    // 用户编号（必填）
    private String userId;

    // 登录名（必填）
    private String loginName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
