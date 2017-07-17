package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622050Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 新增团课
 * @author: asus 
 * @since: 2017年7月17日 下午5:15:03 
 * @history:
 */
public class XN622050 extends AProcessor {
    private IOrgCourseAO orgCourseAO = SpringContextHolder
        .getBean(IOrgCourseAO.class);

    private XN622050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(orgCourseAO.addOrgCourse(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622050Req.class);
        StringValidater.validateBlank(req.getCoachUser(), req.getName(),
            req.getClassDatetime(), req.getSkStartDatetime(),
            req.getSkEndDatetime(), req.getAddress(), req.getContact(),
            req.getPic(), req.getAdvPic(), req.getDescription(),
            req.getUpdater());
        StringValidater.validateAmount(req.getPrice());
        StringValidater.validateNumber(req.getTotalNum());
    }

}
