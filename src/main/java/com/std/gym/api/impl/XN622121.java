package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622121Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 支付私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午6:57:10 
 * @history:
 */
public class XN622121 extends AProcessor {
    private IPerCourseOrderAO perCourseOrderAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622121Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return perCourseOrderAO
            .toPayOrder(req.getOrderCode(), req.getPayType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622121Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getPayType());
    }

}
