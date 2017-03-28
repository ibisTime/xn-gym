package com.std.forum.dto.req;

/**
 * 列表查询视频
 * @author: asus 
 * @since: 2017年3月28日 下午3:23:43 
 * @history:
 */
public class XN610057Req {

    // 名称（选填）
    private String name;

    // 状态（选填）
    private String status;

    // 站点编号（选填）
    private String companyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
