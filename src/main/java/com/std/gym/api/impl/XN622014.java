package com.std.gym.api.impl;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622014Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 下架活动
 * @author: asus 
 * @since: 2017年7月17日 上午10:42:23 
 * @history:
 */
public class XN622014 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622014Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityAO.downActivity(req.getCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622014Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }

}
