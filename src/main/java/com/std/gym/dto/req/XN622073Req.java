package com.std.gym.dto.req;

/**
 * 平台取消订单
 * @author: asus 
 * @since: 2017年7月18日 上午9:47:32 
 * @history:
 */
public class XN622073Req {
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
