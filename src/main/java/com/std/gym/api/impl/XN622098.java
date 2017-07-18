package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622098Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 根据userId详情查询私教
 * @author: asus 
 * @since: 2017年7月18日 下午1:56:38 
 * @history:
 */
public class XN622098 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622098Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return coachAO.getCoachByUserId(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622098Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
