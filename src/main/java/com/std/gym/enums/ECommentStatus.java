package com.std.gym.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ECommentStatus {
    DRAFT("A", "草稿中"), PUBLISHED("B", "已发布"), todoAPPROVE("C1", "不信任待审批"), toReportAPPROVE(
            "C2", "被举报待审批"), APPROVE_YES("D", "审批通过"), APPROVE_NO("E", "待回收"), FILTERED(
            "F", "被过滤"), PUBLISHALL("BD", "已发布和审核通过");

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
