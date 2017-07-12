package com.std.gym.api.impl;

import com.std.gym.ao.IKeywordAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622002Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 修改关键词
 * @author: asus 
 * @since: 2017年7月12日 下午2:41:54 
 * @history:
 */
public class XN622002 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN622002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.editKeyword(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622002Req.class);
        StringValidater.validateBlank(req.getCode(), req.getWord(),
            req.getLevel(), req.getWeight(), req.getReaction(),
            req.getUpdater());
    }
}
