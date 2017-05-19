package com.std.forum.dto.req;

import java.util.Date;

/**
 * 分页查询PV
 * @author: asus 
 * @since: 2017年5月15日 下午3:23:36 
 * @history:
 */
public class XN610405Req extends APageReq{
    // 公司编号
    private String companyCode;
    
    //访问时间
    private String viewDatetime ;
    
    
  

    public String getViewDatetime() {
		return viewDatetime;
	}

	public void setViewDatetime(String viewDatetime) {
		this.viewDatetime = viewDatetime;
	}

	public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    
    
    
}
