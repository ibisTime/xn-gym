package com.std.forum.api.impl;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.DateUtil;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.PageView;
import com.std.forum.dto.req.XN610406Req;
import com.std.forum.dto.req.XN610407Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 详情pv
 * @author William
 * @since  2017年5月15日 下午4:56:51
 * @history
 */
public class XN610407 extends AProcessor {
    private IPageViewAO pageViewAO = SpringContextHolder
        .getBean(IPageViewAO.class);

    private XN610407Req req = null;

    @Override
    public Object doBusiness() throws BizException {
    	return pageViewAO.getPageView(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610407Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
