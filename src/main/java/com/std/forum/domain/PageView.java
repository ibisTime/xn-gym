package com.std.forum.domain;

import com.std.forum.dao.base.ABaseDO;

/**
* 站点访问量
* @author: shan
* @since: 2017年05月15日
* @history:
*/
public class PageView extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 公司编号
	private String companyCode;

	// 访问日期
	private String viewDatetime;

	// 访问数量
	private String pageViewNum;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setViewDatetime(String viewDatetime) {
		this.viewDatetime = viewDatetime;
	}

	public String getViewDatetime() {
		return viewDatetime;
	}

	public void setPageViewNum(String pageViewNum) {
		this.pageViewNum = pageViewNum;
	}

	public String getPageViewNum() {
		return pageViewNum;
	}

}