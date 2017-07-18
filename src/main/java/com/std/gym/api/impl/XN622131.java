package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622131Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午8:35:42 
 * @history:
 */
public class XN622131 extends AProcessor {
    private IPerCourseOrderAO perCourseOrderAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622131Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return perCourseOrderAO.getPerCourseOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622131Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
