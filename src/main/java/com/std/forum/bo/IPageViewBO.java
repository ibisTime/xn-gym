package com.std.forum.bo;

import java.util.List;

import com.std.forum.bo.base.IPaginableBO;
import com.std.forum.domain.PageView;



//CHECK ��鲢��ע�� 
public interface IPageViewBO extends IPaginableBO<PageView> {


	public boolean isPageViewExist(String code);


	public String savePageView(PageView data);


	public int removePageView(String code);


	public int refreshPageView(PageView data);


	public List<PageView> queryPageViewList(PageView condition);


	public PageView getPageView(String code);


}