package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESystemCode {
    SYSTEM_CODE("CD-ZWZJ000012", "系统编号");

    public static Map<String, ESystemCode> getChannelTypeResultMap() {
        Map<String, ESystemCode> map = new HashMap<String, ESystemCode>();
        for (ESystemCode type : ESystemCode.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    ESystemCode(String code, String value) {
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
