package com.std.gym.dto.req;

/**
 * 详情查询参与
 * @author: asus 
 * @since: 2017年9月7日 下午5:29:54 
 * @history:
 */
public class XN622231Req {
    // 当前用户
    private String userId;

    // 编号
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
