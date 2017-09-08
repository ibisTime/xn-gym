package com.std.gym.enums;

/** 
 * 编号前缀
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum EPrefixCode {
    COMMENT("PL", "评论"), KEYWORD("GJ", "关键字"), ACTIVITY("AC", "活动"), ACTIVITYORDER(
            "AOD", "活动订单"), PRAISEITEM("PI", "评论项"), PERCOURSEORDER("PO",
            "私课订单"), PERCOURSE("PC", "私课"), ORGCOURSEORDER("OO", "团课订单"), ORGCOURSE(
            "OC", "团课"), COACH("SJ", "私教"), ATTEND("CY", "参与"), VOTE("TP", "投票");

    EPrefixCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
