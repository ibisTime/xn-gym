/**
 * @Title UserKind.java 
 * @Package com.ibis.pz.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午8:51:05 
 * @version V1.0   
 */
package com.std.gym.enums;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午8:51:05 
 * @history:
 */
public enum EUserKind {
    F1("f1", "前端个人用户/C端用户"), F2("f2", "渠道商/B端用户"), F3("f3", "达人");

    EUserKind(String code, String value) {
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
