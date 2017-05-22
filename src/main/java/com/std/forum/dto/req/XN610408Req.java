package com.std.forum.dto.req;

import java.util.Date;

/**
 * 查询统计情况
 * @author William
 * @since  2017年5月16日 上午10:25:05
 * @history
 */
public class XN610408Req {
    // 公司编号
    private String companyCode;
    
    private String dateStart ;
    
    private String dateEnd ;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

 
    
}
