package com.std.gym.dto.req;

/**
 * 参加
 * @author: asus 
 * @since: 2017年9月7日 下午5:44:13 
 * @history:
 */
public class XN622220Req {
    // 活动编号
    private String activityCode;

    // 登录类型
    private String kind;

    // 登录名
    private String loginName;

    // 密码
    private String loginPwd;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
