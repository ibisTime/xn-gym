package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622250Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 下架
 * @author: asus 
 * @since: 2017年7月18日 下午1:56:38 
 * @history:
 */
public class XN622250 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622250Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        coachAO.putOff(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622250Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
