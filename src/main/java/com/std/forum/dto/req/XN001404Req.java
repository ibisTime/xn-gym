package com.std.forum.dto.req;

import java.util.Date;

/**
 * 统计用户数
 * @author: asus 
 * @since: 2017年5月16日 下午5:40:11 
 * @history:
 */
public class XN001404Req {
    // 创建开始时间
    private String beginDatetime;

    // 创建结束时间
    private String endDatetime;

    // 公司编号
    private String companyCode;

    // 系统编号
    private String systemCode;

	public String getBeginDatetime() {
		return beginDatetime;
	}

	public void setBeginDatetime(String beginDatetime) {
		this.beginDatetime = beginDatetime;
	}

	public String getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

 
}
