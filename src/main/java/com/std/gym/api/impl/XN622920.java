package com.std.gym.api.impl;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622920Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 统计所有未完成的订单
 * @author: asus 
 * @since: 2017年7月25日 下午4:17:37 
 * @history:
 */
public class XN622920 extends AProcessor {
    private IPerCourseOrderAO perCourseAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622920Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return perCourseAO;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622920Req.class);
        StringValidater.validateBlank(req.getApplyUser());
    }

}
