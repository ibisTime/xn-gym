package com.std.forum.bo;

import java.util.Date;
import java.util.List;

import com.std.forum.bo.base.IPaginableBO;
import com.std.forum.domain.PageView;

public interface IPageViewBO extends IPaginableBO<PageView> {

    public boolean isPageViewExist(String code);

    public void savePageView(String companyCode);

    public int refreshPageView(PageView data);

    public List<PageView> queryPageViewList(PageView condition);

    public PageView getPageView(String code);

    public List<PageView> queryPageViewList(String companyCode, Date datetime);

}
