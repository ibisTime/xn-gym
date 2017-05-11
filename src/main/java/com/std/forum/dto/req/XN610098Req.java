package com.std.forum.dto.req;

/**
 * 前端小版块查询
 * @author: william
 * @since: 2017年3月21日 下午7:50:31 
 * @history:
 */
public class XN610098Req extends APageReq{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 站点编号
    private String companyCode;

    private String status ;
    
    public String getCompanyCode() {
        return companyCode;
    }
    

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
