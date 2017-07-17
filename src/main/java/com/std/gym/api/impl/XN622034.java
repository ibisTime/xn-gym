package com.std.gym.api.impl;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622034Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 用户申请退款
 * @author: asus 
 * @since: 2017年7月17日 下午3:07:44 
 * @history:
 */
public class XN622034 extends AProcessor {
    private IActivityOrderAO activityOrderBO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622034Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityOrderBO.applyRefund(req.getOrderCode(), req.getApplyUser(),
            req.getApplyNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622034Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getApplyUser());
    }

}
