package com.std.gym.api.impl;

import com.std.gym.ao.IVoteAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622240Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 投票
 * @author: asus 
 * @since: 2017年9月7日 下午9:11:00 
 * @history:
 */
public class XN622240 extends AProcessor {
    private IVoteAO voteAO = SpringContextHolder.getBean(IVoteAO.class);

    private XN622240Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(voteAO.addVote(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622240Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getAttendCode());
    }

}
