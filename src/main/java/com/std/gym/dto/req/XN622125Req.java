package com.std.gym.dto.req;

/**
 * 用户取消订单
 * @author: asus 
 * @since: 2017年7月18日 下午7:18:43 
 * @history:
 */
public class XN622125Req {
    // 订单编号(必填)
    private String orderCode;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
