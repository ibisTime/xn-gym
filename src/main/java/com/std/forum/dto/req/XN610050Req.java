package com.std.forum.dto.req;

/**
 * 新增视频
 * @author: asus 
 * @since: 2017年3月28日 下午2:35:46 
 * @history:
 */
public class XN610050Req {
    // 名称（必填）
    private String name;

    // url(必填)
    private String url;

    // 序号（必填）
    private String orderNo;

    // 站点编号（必填）
    private String companyCode;

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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
