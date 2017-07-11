package com.std.gym.api.impl;

import com.std.gym.ao.IKeywordAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN610001Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/** 
 * 删除关键词
 * @author: zuixian 
 * @since: 2016年9月28日 下午1:53:20 
 * @history:
 */
public class XN610001 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN610001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.dropKeyword(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610001Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
