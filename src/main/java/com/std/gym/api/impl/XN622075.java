package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622075Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 审批退款
 * @author: asus 
 * @since: 2017年7月18日 上午10:12:15 
 * @history:
 */
public class XN622075 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622075Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orgCourseOrderAO.approveRefund(req.getOrderCode(), req.getResult(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622075Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getResult(),
            req.getUpdater());
    }
}
