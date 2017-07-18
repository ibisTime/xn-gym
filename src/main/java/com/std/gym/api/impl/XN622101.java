package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622101Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 删除私课
 * @author: asus 
 * @since: 2017年7月18日 下午4:16:14 
 * @history:
 */
public class XN622101 extends AProcessor {
    private IPerCourseAO perCourseAO = SpringContextHolder
        .getBean(IPerCourseAO.class);

    private XN622101Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        perCourseAO.dropPerCourse(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622101Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
