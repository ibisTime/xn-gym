package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author: shan 
 * @since: 2016年12月14日 下午1:09:48 
 * @history:
 */
public enum EPerCourseOrderStatus {
    NOTPAY("0", "待付款"), PAYSUCCESS("1", "已支付待接单"), RECEIVER_ORDER("2", "已接单待上课"), HAVE_CLASS(
            "3", "已上课待填表"), TO_FILL_FORM("4", "已填表待下课"), CLASS_OVER("5",
            "已下课待评价"), USER_CANCEL("6", "用户取消"), PLAT_CANCEL("7", "B端取消"), FINISH(
            "8", "已完成");
    public static Map<String, EPerCourseOrderStatus> getEOrderStatusResultMap() {
        Map<String, EPerCourseOrderStatus> map = new HashMap<String, EPerCourseOrderStatus>();
        for (EPerCourseOrderStatus status : EPerCourseOrderStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EPerCourseOrderStatus(String code, String value) {
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
