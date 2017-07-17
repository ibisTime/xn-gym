package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622061Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询团课
 * @author: asus 
 * @since: 2017年7月17日 下午7:33:35 
 * @history:
 */
public class XN622061 extends AProcessor {
    private IOrgCourseAO orgCourseAO = SpringContextHolder
        .getBean(IOrgCourseAO.class);

    private XN622061Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orgCourseAO.getOrgCourse(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622061Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
