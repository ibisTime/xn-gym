package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:15:22 
 * @history:
 */
public enum EBizType {
    AJ_REG("01", "注册送积分"), AJ_SIGN("02", "每日签到"), AJ_SCTX_FIRST("03", "首次上传头像"), AJ_ZLWS_FIRST(
            "04", "首次完善资料"),

    AJ_HDGM("HDGM", "活动购买"), AJ_HDGMTK("HDGMTK", "活动购买退款"), HDGMSJF("HDGMSJF",
            "活动购买送积分"),

    AJ_TKGM("TKGM", "团课购买"), AJ_TKGMTK("TKGMTK", "团课购买退款"), KCGMSJF("KCGMSJF",
            "团课购买加积分"),

    AJ_SKGM("SKGM", "私课购买"), AJ_SKGMTK("SKGMTK", "私课购买退款"), SKGMSJF("SKGMSJF",
            "私课购买加积分"),

    AJ_DRGM("DRGM", "达人课程购买"), AJ_DRGMTK("DRGMTK", "达人课程购买退款"), DRGMSJF(
            "DRGMSJF", "达人课程购买加积分"),

    TJ("TJ", "推荐得分成"), SKJFC("SKJFC", "私课教练分成"), TTJFC("TTJFC", "团课教练分成"),

    WYSJFC("WYSJFC", "用户违约私教获得分成"), WYTTJFC("WYTTJFC", "用户违约团课教练获得分成"), WYDRFC(
            "WYDRFC", "用户违约达人获得分成"), ;

    public static Map<String, EBizType> getBizTypeMap() {
        Map<String, EBizType> map = new HashMap<String, EBizType>();
        for (EBizType bizType : EBizType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EBizType(String code, String value) {
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
