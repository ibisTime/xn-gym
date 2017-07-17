package com.std.gym.api.impl;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622032Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 用户取消订单
 * @author: asus 
 * @since: 2017年7月17日 下午2:38:22 
 * @history:
 */
public class XN622032 extends AProcessor {
    private IActivityOrderAO activityOrderBO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622032Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityOrderBO.userCancel(req.getOrderCode(), req.getApplyUser());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622032Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getApplyUser());
    }

}
