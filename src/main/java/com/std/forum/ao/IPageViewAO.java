package com.std.forum.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.PageView;

@Component
public interface IPageViewAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPageView(PageView data);

    public int dropPageView(String code);

    public int editPageView(PageView data);

    public Paginable<PageView> queryPageViewPage(int start, int limit,
            PageView condition);

    public List<PageView> queryPageViewList(PageView condition);

    public PageView getPageView(String code);

}
