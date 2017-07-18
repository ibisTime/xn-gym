package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622111Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询私课
 * @author: asus 
 * @since: 2017年7月18日 下午4:49:33 
 * @history:
 */
public class XN622111 extends AProcessor {
    private IPerCourseAO perCourseAO = SpringContextHolder
        .getBean(IPerCourseAO.class);

    private XN622111Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return perCourseAO.getPerCourse(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622111Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
