package com.std.gym.api.impl;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622016Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 开始活动
 * @author: asus 
 * @since: 2017年7月17日 上午10:47:18 
 * @history:
 */
public class XN622016 extends AProcessor {
    private IActivityOrderAO activityOrderAO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622016Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityOrderAO.beginActivity(req.getCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622016Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }
}
