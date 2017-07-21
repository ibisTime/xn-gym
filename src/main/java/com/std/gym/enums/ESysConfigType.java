package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数类型
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum ESysConfigType {
    INTEGRATE("1", "积分规则"), ITEM_SCORE("2", "评论项目"), LEVER_RULE("3", "等级规则");

    public static Map<String, ESysConfigType> getDictTypeMap() {
        Map<String, ESysConfigType> map = new HashMap<String, ESysConfigType>();
        for (ESysConfigType activityStatus : ESysConfigType.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    ESysConfigType(String code, String value) {
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
