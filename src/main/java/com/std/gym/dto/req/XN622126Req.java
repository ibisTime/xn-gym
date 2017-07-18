package com.std.gym.dto.req;

/**
 * 私教取消订单
 * @author: asus 
 * @since: 2017年7月18日 下午7:18:43 
 * @history:
 */
public class XN622126Req {
    // 订单编号
    private String orderCode;

    // 更新人
    private String updater;

    // 备注
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
