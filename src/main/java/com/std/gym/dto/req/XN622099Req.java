package com.std.gym.dto.req;

/**
 * 上架
 * @author: asus 
 * @since: 2017年7月18日 下午1:56:38 
 * @history:
 */
public class XN622099Req {
    // 编号
    private String code;

    // UI位置
    private String location;

    // UI顺序
    private String orderNo;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
