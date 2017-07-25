package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622056Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 团课开始上课
 * @author: asus 
 * @since: 2017年7月17日 下午7:01:18 
 * @history:
 */
public class XN622056 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622056Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orgCourseOrderAO.beginOrgCourse(req.getCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622056Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }

}
