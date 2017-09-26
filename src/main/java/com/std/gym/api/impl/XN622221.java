package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622221Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

public class XN622221 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622221Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(coachAO.registerCoach(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622221Req.class);
        StringValidater.validateBlank(req.getKind(), req.getRealName(),
            req.getMobile(), req.getGender(), req.getAge(), req.getDuration(),
            req.getActivityCode(), req.getProvince(), req.getCity(),
            req.getAdvPic(), req.getPdf());
    }
}
