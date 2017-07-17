package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622053Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 上架团课
 * @author: asus 
 * @since: 2017年7月17日 下午6:28:21 
 * @history:
 */
public class XN622053 extends AProcessor {
    private IOrgCourseAO orgCourseAO = SpringContextHolder
        .getBean(IOrgCourseAO.class);

    private XN622053Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orgCourseAO.putOn(req.getCode(), req.getLocation(),
            StringValidater.toInteger(req.getOrderNo()), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622053Req.class);
        StringValidater.validateBlank(req.getCode(), req.getLocation(),
            req.getUpdater());
        StringValidater.validateNumber(req.getOrderNo());
    }

}
