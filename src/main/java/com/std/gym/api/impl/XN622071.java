package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622071Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 支付团课订单
 * @author: asus 
 * @since: 2017年7月17日 下午8:12:17 
 * @history:
 */
public class XN622071 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622071Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orgCourseOrderAO
            .toPayOrder(req.getOrderCode(), req.getPayType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622071Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getPayType());
    }
}
