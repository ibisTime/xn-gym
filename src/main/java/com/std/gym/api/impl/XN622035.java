package com.std.gym.api.impl;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622035Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 审批退款
 * @author: asus 
 * @since: 2017年7月17日 下午3:25:16 
 * @history:
 */
public class XN622035 extends AProcessor {
    private IActivityOrderAO activityOrderBO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622035Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityOrderBO.approveRefund(req.getOrderCode(), req.getResult(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622035Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getResult(),
            req.getUpdater());
    }

}
