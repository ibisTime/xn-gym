package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622099Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * @author: asus 
 * @since: 2017年7月18日 下午1:56:38 
 * @history:
 */
public class XN622099 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622099Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        coachAO
            .editLocation(req.getCode(), req.getLocation(), req.getOrderNo());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622099Req.class);
        StringValidater.validateBlank(req.getCode(), req.getLocation());
        StringValidater.toInteger(req.getOrderNo());
    }
}
