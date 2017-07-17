package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622055Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 团课截止报名
 * @author: asus 
 * @since: 2017年7月17日 下午7:01:18 
 * @history:
 */
public class XN622055 extends AProcessor {
    private IOrgCourseAO orgCourseAO = SpringContextHolder
        .getBean(IOrgCourseAO.class);

    private XN622055Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orgCourseAO.stopSign(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622055Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }

}
