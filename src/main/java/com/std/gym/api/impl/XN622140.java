package com.std.gym.api.impl;

import com.std.gym.ao.ICommentAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622140Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 评论
 * @author: asus 
 * @since: 2017年7月19日 上午11:40:38 
 * @history:
 */
public class XN622140 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN622140Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(commentAO.addComment(req.getContent(),
            req.getItemScoreList(), req.getCommer(), req.getProductCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622140Req.class);
        StringValidater.validateBlank(req.getProductCode(), req.getContent(),
            req.getCommer());
    }
}
