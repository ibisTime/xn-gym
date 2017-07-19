package com.std.gym.ao;

import java.util.List;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Keyword;
import com.std.gym.dto.req.XN622000Req;
import com.std.gym.dto.req.XN622002Req;

public interface IKeywordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addKeyword(XN622000Req req);

    public void dropKeyword(String code);

    public void editKeyword(XN622002Req req);

    public Paginable<Keyword> queryKeywordPage(int start, int limit,
            Keyword condition);

    public Keyword getKeyword(String code);

    public void addKeyword(List<XN622000Req> reqList);

}
