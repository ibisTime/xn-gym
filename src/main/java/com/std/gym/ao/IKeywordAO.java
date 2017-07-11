package com.std.gym.ao;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Keyword;
import com.std.gym.dto.req.XN610000Req;
import com.std.gym.dto.req.XN610002Req;

public interface IKeywordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addKeyword(XN610000Req req);

    public void dropKeyword(String code);

    public void editKeyword(XN610002Req req);

    public Paginable<Keyword> queryKeywordPage(int start, int limit,
            Keyword condition);

    public Keyword getKeyword(String code);

}
