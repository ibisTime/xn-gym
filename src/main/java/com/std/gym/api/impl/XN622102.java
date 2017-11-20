package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622102Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 修改私课
 * @author: asus 
 * @since: 2017年7月18日 下午3:48:31 
 * @history:
 */
public class XN622102 extends AProcessor {
    private IPerCourseAO perCourseAO = SpringContextHolder
        .getBean(IPerCourseAO.class);

    private XN622102Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        perCourseAO.editPerCourse(req);
        return new PKCodeRes();
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622102Req.class);
        StringValidater.validateBlank(req.getCode(), req.getSkStartDatetime(),
            req.getSkEndDatetime());
        StringValidater.validateAmount(req.getPrice());

    }
}
