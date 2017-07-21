package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum ESysConfigCkey {
    LXJL("LXJL", "零星教练"), YXJL("YXJL", "一星教练"), EXJL("EXJL", "二星教练"), SAXJL(
            "SAXJL", "三星教练"), SXJL("SXJL", "四星教练"), WXJL("WXJL", "五星教练"), HKFC(
            "HKFC", "推荐分成"), SJFC("SJFC", "私教分成"), WY("WY", "违约扣除比例"), TTJFC(
            "TTJFC", "团课教练分成"), ;

    public static Map<String, ESysConfigCkey> getDictTypeMap() {
        Map<String, ESysConfigCkey> map = new HashMap<String, ESysConfigCkey>();
        for (ESysConfigCkey activityStatus : ESysConfigCkey.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    ESysConfigCkey(String code, String value) {
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
