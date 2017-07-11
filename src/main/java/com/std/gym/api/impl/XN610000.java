package com.std.gym.api.impl;

import com.std.gym.ao.IKeywordAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN610000Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/** 
 * 新增关键词
 * @author: zuixian 
 * @since: 2016年9月28日 下午1:53:13 
 * @history:
 */
public class XN610000 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN610000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(keywordAO.addKeyword(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610000Req.class);
        StringValidater.validateBlank(req.getWord(), req.getLevel(),
            req.getWeight(), req.getReaction(), req.getUpdater());
    }
}
