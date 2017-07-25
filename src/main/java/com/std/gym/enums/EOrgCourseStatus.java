package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动状态
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum EOrgCourseStatus {
    DRAFT("0", "草稿"), ONLINE("1", "上架团课"), STOP("2", "截止团课"), OFFLINE("3",
            "下架团课"), BEGIN("4", "团课开始上课"), END("5", "团课下课");

    public static Map<String, EOrgCourseStatus> getDictTypeMap() {
        Map<String, EOrgCourseStatus> map = new HashMap<String, EOrgCourseStatus>();
        for (EOrgCourseStatus activityStatus : EOrgCourseStatus.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    EOrgCourseStatus(String code, String value) {
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
