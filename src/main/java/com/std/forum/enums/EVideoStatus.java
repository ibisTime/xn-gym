package com.std.forum.enums;

import java.util.HashMap;
import java.util.Map;

public enum EVideoStatus {
    TODO("1", "未上架"), DOING("2", "已上架"), DONE("3", "已下架");
    public static Map<String, EVideoStatus> getBelongMap() {
        Map<String, EVideoStatus> map = new HashMap<String, EVideoStatus>();
        for (EVideoStatus status : EVideoStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EVideoStatus(String code, String value) {
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
