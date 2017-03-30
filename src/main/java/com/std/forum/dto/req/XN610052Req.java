package com.std.forum.dto.req;

/**
 * 修改视频
 * @author: asus 
 * @since: 2017年3月28日 下午2:35:46 
 * @history:
 */
public class XN610052Req {
    // 编号（必填）
    private String code;

    // 名称（必填）
    private String name;

    // 图片
    private String pic;

    // url
    private String url;

    // 序号（必填）
    private String orderNo;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
