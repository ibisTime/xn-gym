package com.std.forum.dto.req;

import java.util.Date;

/**
 * 列表查PV
 * @author William
 * @since  2017年5月15日 下午7:21:57
 * @history
 */
public class XN610406Req {
    // 公司编号
    private String companyCode;
    
    //查询pv开始时间
    private String dateStart ;
    
    //查询pv结束时间
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
