package com.std.gym.api.impl;

import com.std.gym.ao.ICommentAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622141Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 删除评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:08:53 
 * @history:
 */
public class XN622141 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN622141Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        commentAO.dropComment(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622141Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
