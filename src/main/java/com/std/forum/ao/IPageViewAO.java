package com.std.forum.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.PageView;
import com.std.forum.dto.req.XN610408Req;
import com.std.forum.dto.res.XN610408Res;

@Component
public interface IPageViewAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void addPageView(String companyCode);

    public void editPageView(PageView data);

    public Paginable<PageView> queryPageViewPage(int start, int limit,
            PageView condition);

    public List<PageView> queryPageViewList(PageView condition);

    public PageView getPageView(String code);
    
    public PageView getPageView(PageView condition);
    
    public XN610408Res queryTotalPage(XN610408Req req);
 
}
