package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622081Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询团课订单
 * @author: asus 
 * @since: 2017年7月18日 上午10:39:05 
 * @history:
 */
public class XN622081 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622081Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orgCourseOrderAO.getOrgCourseOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622081Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
