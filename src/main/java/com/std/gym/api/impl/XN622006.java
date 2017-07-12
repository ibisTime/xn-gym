package com.std.gym.api.impl;

import com.std.gym.ao.IKeywordAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622006Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询关键字
 * @author: asus 
 * @since: 2017年7月12日 下午2:43:47 
 * @history:
 */
public class XN622006 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN622006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return keywordAO.getKeyword(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622006Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
