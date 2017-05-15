package com.std.forum.api.impl;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610400Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 新增PV
 * @author: asus 
 * @since: 2017年5月15日 下午3:24:59 
 * @history:
 */
public class XN610400 extends AProcessor {
    private IPageViewAO pageViewAO = SpringContextHolder
        .getBean(IPageViewAO.class);

    private XN610400Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        pageViewAO.addPageView(req.getCompanyCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610400Req.class);
        StringValidater.validateBlank(req.getCompanyCode());
    }
}
