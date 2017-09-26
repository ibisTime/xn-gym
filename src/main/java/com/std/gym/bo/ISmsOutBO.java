package com.std.gym.bo;

public interface ISmsOutBO {
    /**
     * 发送短信
     * @param userId
     * @param content 
     * @create: 2015年11月17日 下午3:37:54 myb858
     * @history:
     */
    void sentContent(String userId, String content);

    /**
     * 发送指定内容短信(系统)
     * @param mobile
     * @param content
     * @create: 2017年2月13日 下午3:03:24 xieyj
     * @history:
     */
    public void sendSmsOut(String mobile, String content);
}
