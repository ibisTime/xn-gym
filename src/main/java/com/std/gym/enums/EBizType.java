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
            "04", "首次完善资料"), AJ_HDGM("HDGM", "活动购买"), AJ_HDGMTK("HDGMTK",
            "活动购买退款"), AJ_TKGM("TKGM", "团课购买"), AJ_TKGMTK("TKGMTK", "团课购买退款"), AJ_SKGM(
            "SKGM", "私课购买"), AJ_SKGMTK("SKGMTK", "私课购买退款"), HDGM("HDGMSJF",
            "活动购买送积分"), SKGM("SKGMSJF", "私课购买加积分"), KCGM("KCGMSJF", "团课购买加积分"), TJ(
            "TJ", "推荐得分成");

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
