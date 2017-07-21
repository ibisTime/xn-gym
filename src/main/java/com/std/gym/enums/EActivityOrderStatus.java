package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author: shan 
 * @since: 2016年12月14日 下午1:09:48 
 * @history:
 */
public enum EActivityOrderStatus {
    NOTPAY("0", "待付款"), PAYSUCCESS("1", "付款成功"), USER_CANCEL("2", "用户取消订单"), PLAT_CANCEL(
            "3", "平台取消订单"), APPLY_REFUND("4", "退款申请"), REFUND_YES("5", "退款成功"), REFUND_NO(
            "6", "退款失败"), TO_COMMENT("7", "待评价"), END("8", "已完成");
    public static Map<String, EActivityOrderStatus> getEOrderStatusResultMap() {
        Map<String, EActivityOrderStatus> map = new HashMap<String, EActivityOrderStatus>();
        for (EActivityOrderStatus status : EActivityOrderStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EActivityOrderStatus(String code, String value) {
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
