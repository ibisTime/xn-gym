package com.std.gym.api.impl;

import com.std.gym.ao.ICommentAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.dto.req.XN622147Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:48:36 
 * @history:
 */
public class XN622147 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN622147Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return commentAO.avgCommentScore(req.getCoachCode(),
            req.getProductCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622147Req.class);
    }

}
