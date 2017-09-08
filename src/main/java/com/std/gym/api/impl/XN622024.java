package com.std.gym.api.impl;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622024Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

public class XN622024 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622024Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityAO.modifyVote(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622024Req.class);
        StringValidater.validateBlank(req.getCode(), req.getTitle(),
            req.getPic(), req.getDescription(), req.getStartDatetime(),
            req.getEndDatetime(), req.getUpdater());
    }
}
