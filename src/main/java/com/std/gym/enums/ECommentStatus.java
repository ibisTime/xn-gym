package com.std.gym.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ECommentStatus {
    PUBLISHED("A", "已发布"), APPROVE_YES("B", "审批通过"), APPROVE_NO("C", "审批不通过"), FILTERED(
            "D", "被过滤"), PUBLISHALL("AB", "已发布和审核通过");

    ECommentStatus(String code, String value) {
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
