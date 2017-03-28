package com.std.forum.dto.req;

/**
 * 分页查询视频
 * @author: asus 
 * @since: 2017年3月28日 下午3:23:43 
 * @history:
 */
public class XN610055Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

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
