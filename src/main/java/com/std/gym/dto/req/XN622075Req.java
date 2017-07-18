package com.std.gym.dto.req;

/**
 * 审批退款
 * @author: asus 
 * @since: 2017年7月18日 上午10:12:15 
 * @history:
 */
public class XN622075Req {
    // 订单编号
    private String orderCode;

    // 审核结果
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
