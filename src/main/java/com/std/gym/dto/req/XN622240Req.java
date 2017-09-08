package com.std.gym.dto.req;

/**
 * 投票
 * @author: asus 
 * @since: 2017年9月7日 下午9:11:00 
 * @history:
 */
public class XN622240Req {

    // 投票人
    private String userId;

    // 投标编号
    private String attendCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttendCode() {
        return attendCode;
    }

    public void setAttendCode(String attendCode) {
        this.attendCode = attendCode;
    }

}
