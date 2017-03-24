package com.std.forum.ao;

import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Keyword;
import com.std.forum.dto.req.XN610000Req;
import com.std.forum.dto.req.XN610002Req;

public interface IKeywordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addKeyword(XN610000Req req);

    public void dropKeyword(String code);

    public void editKeyword(XN610002Req req);

    public Paginable<Keyword> queryKeywordPage(int start, int limit,
            Keyword condition);

    public Keyword getKeyword(String code);

}
