package com.std.gym.api.impl;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622031Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 支付活动订单
 * @author: asus 
 * @since: 2017年7月17日 下午1:54:04 
 * @history:
 */
public class XN622031 extends AProcessor {
    private IActivityOrderAO activityOrderBO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622031Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return activityOrderBO.orderPay(req.getOrderCode(), req.getPayType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622031Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getPayType());
    }

}
