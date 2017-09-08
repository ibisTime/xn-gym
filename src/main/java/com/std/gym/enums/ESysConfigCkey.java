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
            "SAXJL", "三星教练"), SXJL("SXJL", "四星教练"), WXJL("WXJL", "五星教练"),

    LXDR("LXDR", "零星达人"), YXDR("YXDR", "一星达人"), EXDR("EXDR", "二星达人"), SAXDR(
            "SAXDR", "三星达人"), SXDR("SXDR", "四星达人"), WXDR("WXDR", "五星达人"),

    HKFC("HKFC", "推荐分成"), SJFC("SJFC", "私教分成"), WY("WY", "违约扣除比例"), DRFC(
            "DRFC", "达人分成"),

    TTJFC("TTJFC", "团课教练分成"), WYSJFC("WYSJFC", "用户违约私教获得分成比例"), WYTTJFC(
            "WYTTJFC", "用户违约团课教练分成"), WYDRFC("WYDRFC", "用户违约达人获得分成比例"), ;

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
