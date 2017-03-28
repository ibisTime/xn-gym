package com.std.forum.dto.req;

/**
 * 视频上架
 * @author: asus 
 * @since: 2017年3月28日 下午3:42:58 
 * @history:
 */
public class XN610053Req {
    // 编号（必填）
    private String code;

    // 序号（必填）
    private String orderNo;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
