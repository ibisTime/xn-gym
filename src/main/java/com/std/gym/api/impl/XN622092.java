package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622092Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 审核私教信息
 * @author: asus 
 * @since: 2017年7月18日 下午1:11:13 
 * @history:
 */
public class XN622092 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622092Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        coachAO.approveCoach(req.getCode(), req.getResult(),
            req.getCreditAmount(), req.getApprover(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622092Req.class);
        StringValidater.validateBlank(req.getCode(), req.getResult(),
            req.getApprover());
    }
}
