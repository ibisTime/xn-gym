package com.std.gym.api.impl;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622030Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 活动报名
 * @author: asus 
 * @since: 2017年7月17日 下午1:14:54 
 * @history:
 */
public class XN622030 extends AProcessor {
    private IActivityOrderAO activityOrderBO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622030Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(activityOrderBO.addActivityOrder(
            req.getActivityCode(),
            StringValidater.toInteger(req.getQuantity()), req.getApplyUser(),
            req.getApplyNote(), req.getMobile()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622030Req.class);
        StringValidater.validateBlank(req.getActivityCode(),
            req.getApplyUser(), req.getMobile());
        StringValidater.validateNumber(req.getQuantity());
    }

}
