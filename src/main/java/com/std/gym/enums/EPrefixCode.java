package com.std.gym.enums;

/** 
 * 编号前缀
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum EPrefixCode {
    COMMENT("PL", "评论"), KEYWORD("GJ", "关键字"), ;

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
