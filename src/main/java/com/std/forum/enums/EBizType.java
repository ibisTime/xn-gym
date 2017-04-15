package com.std.forum.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:15:22 
 * @history:
 */
public enum EBizType {
    AJ_REG("01", "注册送积分"), AJ_SIGN("02", "每日签到"), AJ_SCTX_FIRST("03", "首次上传头像"), AJ_ZLWS_FIRST(
            "04", "首次完善资料"), AJ_TZFB("CSW01", "帖子发布"), AJ_PLFB("CSW02", "评论发布"), AJ_DATZ(
            "CSW03", "打赏帖子"), AJ_JHT("CSW04", "精华帖"), AJ_TTT("CSW05", "头条帖"), AJ_ZDT(
            "CSW06", "置顶帖"), AJ_TZWG("CSW07", "帖子违规"), AJ_PLWG("CSW08", "评论违规");

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
