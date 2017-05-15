package com.std.forum.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.bo.IPageViewBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.common.DateUtil;
import com.std.forum.domain.PageView;
import com.std.forum.exception.BizException;

@Service
public class PageViewAOImpl implements IPageViewAO {

    @Autowired
    private IPageViewBO pageViewBO;

    @Override
    public void addPageView(String companyCode) {
        List<PageView> pageViewList = pageViewBO.queryPageViewList(companyCode,
            DateUtil.strToDate(
                DateUtil.getToday(DateUtil.FRONT_DATE_FORMAT_STRING),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        if (CollectionUtils.isEmpty(pageViewList)) {
            pageViewBO.savePageView(companyCode);
        } else {
            PageView pageView = pageViewList.get(0);
            pageViewBO.refreshPageView(pageView);
        }
    }

    @Override
    public void editPageView(PageView data) {
        if (!pageViewBO.isPageViewExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        pageViewBO.refreshPageView(data);
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
