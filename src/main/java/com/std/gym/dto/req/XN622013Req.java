package com.std.gym.dto.req;

/**
 * 上架活动
 * @author: asus 
 * @since: 2017年7月17日 上午10:27:19 
 * @history:
 */
public class XN622013Req {
    // 编号
    private String code;

    // UI位置
    private String location;

    // UI顺序
    private String orderNo;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
