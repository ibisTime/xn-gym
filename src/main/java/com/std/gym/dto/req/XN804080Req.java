package com.std.gym.dto.req;

public class XN804080Req {
    // 类型(选填)
    private String type;

    // 接受短信的手机号--必填
    private String mobile;

    // 短信内容--必填
    private String content;

    // 发送时间--非必填
    private String sendDatetime;

    // 公司编号(选填)
    private String companyCode;

    // 系统编号(必填)
    private String systemCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
