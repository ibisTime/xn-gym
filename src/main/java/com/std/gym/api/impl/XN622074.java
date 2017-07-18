package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622074Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 用户申请退款
 * @author: asus 
 * @since: 2017年7月18日 上午10:03:26 
 * @history:
 */
public class XN622074 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622074Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orgCourseOrderAO.applyRefund(req.getOrderCode(), req.getApplyUser(),
            req.getApplyNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622074Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getApplyUser(),
            req.getApplyNote());
    }

}
