package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622070Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 团课报名
 * @author: asus 
 * @since: 2017年7月17日 下午7:51:58 
 * @history:
 */
public class XN622070 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622070Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(orgCourseOrderAO.commitOrder(
            req.getOrgCourseCode(),
            StringValidater.toInteger(req.getQuantity()), req.getApplyUser(),
            req.getMobile(), req.getApplyNote()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622070Req.class);
        StringValidater.validateBlank(req.getOrgCourseCode(),
            req.getApplyUser(), req.getMobile());
        StringValidater.validateNumber(req.getQuantity());
    }

}
