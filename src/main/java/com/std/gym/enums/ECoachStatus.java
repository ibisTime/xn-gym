package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 私教状态
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum ECoachStatus {
    TO_APPROVE("0", "待审批"), APPROVE_YES("1", "审批通过"), APPROVE_NO("2", "审批不通过"), PUTON(
            "3", "上架"), PUTOFF("4", "下架");

    public static Map<String, ECoachStatus> getDictTypeMap() {
        Map<String, ECoachStatus> map = new HashMap<String, ECoachStatus>();
        for (ECoachStatus activityStatus : ECoachStatus.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    ECoachStatus(String code, String value) {
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
