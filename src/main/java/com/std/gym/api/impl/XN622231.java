package com.std.gym.api.impl;

import com.std.gym.ao.IAttendAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622231Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询参与
 * @author: asus 
 * @since: 2017年9月7日 下午5:29:54 
 * @history:
 */
public class XN622231 extends AProcessor {
    private IAttendAO attendAO = SpringContextHolder.getBean(IAttendAO.class);

    private XN622231Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return attendAO.getAttend(req.getCode(), req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622231Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
