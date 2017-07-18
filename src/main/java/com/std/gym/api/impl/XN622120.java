package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622120Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 提交私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午5:13:32 
 * @history:
 */
public class XN622120 extends AProcessor {
    private IPerCourseOrderAO perCourseOrderAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(perCourseOrderAO.commitOrder(req.getApplyUser(),
            req.getAddress(), req.getMobile(), req.getPerCourseCode(),
            StringValidater.toInteger(req.getQuantity()), req.getApplyNote()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622120Req.class);
        StringValidater.validateBlank(req.getApplyUser(), req.getAddress(),
            req.getMobile(), req.getPerCourseCode());
        StringValidater.validateNumber(req.getQuantity());
    }

}
