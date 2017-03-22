package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610120Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 阅读帖子
 * @author: xieyj 
 * @since: 2016年10月23日 下午9:19:38 
 * @history:
 */
public class XN610120 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        postAO.readPost(req.getPostCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610120Req.class);
        StringValidater.validateBlank(req.getPostCode());
    }
}
