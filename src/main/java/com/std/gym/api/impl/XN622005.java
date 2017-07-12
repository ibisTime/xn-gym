package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IKeywordAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Keyword;
import com.std.gym.dto.req.XN622005Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询关键字
 * @author: asus 
 * @since: 2017年7月12日 下午2:43:33 
 * @history:
 */
public class XN622005 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN622005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Keyword condition = new Keyword();
        condition.setWordForQuery(req.getWord());
        condition.setLevel(req.getLevel());
        condition.setReaction(req.getReaction());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IKeywordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return keywordAO.queryKeywordPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622005Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
