package com.std.forum.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.bo.IPageViewBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.PageView;
import com.std.forum.exception.BizException;



//CHECK ��鲢��ע�� 
@Service
public class PageViewAOImpl implements IPageViewAO {

	@Autowired
	private IPageViewBO pageViewBO;

	@Override
	public String addPageView(PageView data) {
		return pageViewBO.savePageView(data);
	}

	@Override
	public int editPageView(PageView data) {
		if (!pageViewBO.isPageViewExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return pageViewBO.refreshPageView(data);
	}

	@Override
	public int dropPageView(String code) {
		if (!pageViewBO.isPageViewExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return pageViewBO.removePageView(code);
	}

	@Override
	public Paginable<PageView> queryPageViewPage(int start, int limit,
			PageView condition) {
		return pageViewBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<PageView> queryPageViewList(PageView condition) {
		return pageViewBO.queryPageViewList(condition);
	}

	@Override
	public PageView getPageView(String code) {
		return pageViewBO.getPageView(code);
	}
}