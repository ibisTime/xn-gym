package com.std.forum.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESysAccount {
    SYS_ACCOUNT("SYS_ACCOUNT", "系统账号");

    public static Map<String, ESysAccount> getChannelTypeResultMap() {
        Map<String, ESysAccount> map = new HashMap<String, ESysAccount>();
        for (ESysAccount type : ESysAccount.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    ESysAccount(String code, String value) {
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
