package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610127Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 修改登录名
 * @author: asus 
 * @since: 2017年5月9日 下午6:55:29 
 * @history:
 */
public class XN610127 extends AProcessor {
    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610127Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        postAO.modifyUser(req.getUserId(), req.getLoginName());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610127Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getLoginName());
    }

}
