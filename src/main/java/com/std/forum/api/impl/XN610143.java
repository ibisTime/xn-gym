package com.std.forum.api.impl;

import com.std.forum.ao.IPostTalkAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610143Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 打赏列表查
 * @author: asus 
 * @since: 2017年3月27日 下午1:52:37 
 * @history:
 */
public class XN610143 extends AProcessor {
    private IPostTalkAO postTalkAO = SpringContextHolder
        .getBean(IPostTalkAO.class);

    private XN610143Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return postTalkAO.queryPostTalkList(req.getPostCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610143Req.class);
        StringValidater.validateBlank(req.getPostCode());
    }

}
