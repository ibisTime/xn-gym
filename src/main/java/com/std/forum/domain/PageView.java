package com.std.forum.domain;

import java.util.Date;

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
    private Date viewDatetime;

    // 访问数量
    private Integer pageViewNum;

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

    public Date getViewDatetime() {
        return viewDatetime;
    }

    public void setViewDatetime(Date viewDatetime) {
        this.viewDatetime = viewDatetime;
    }

    public Integer getPageViewNum() {
        return pageViewNum;
    }

    public void setPageViewNum(Integer pageViewNum) {
        this.pageViewNum = pageViewNum;
    }

}
