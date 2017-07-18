package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622122Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 私教接单
 * @author: asus 
 * @since: 2017年7月18日 下午7:21:58 
 * @history:
 */
public class XN622122 extends AProcessor {
    private IPerCourseOrderAO perCourseOrderAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622122Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        perCourseOrderAO.receiverOrder(req.getOrderCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622122Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getUpdater());
    }

}
