package com.std.forum.dto.req;

/**
 * 设置/取消帖子为置顶，精华，头条等属性
 * @author: xieyj 
 * @since: 2016年10月13日 下午3:28:37 
 * @history:
 */
public class XN610117Req {

    // 帖子编号（必填）
    private String code;

    // UI位置（必填）
    private String location;

    // ui顺序
    private String orderNo;

    // 类型(1置顶取消 2 精华取消 3 头条取消)
    private String type;

    // 操作人
    private String updater;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
