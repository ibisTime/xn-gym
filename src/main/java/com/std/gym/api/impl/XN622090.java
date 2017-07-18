package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622090Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 私教申请
 * @author: asus 
 * @since: 2017年7月18日 上午11:12:21 
 * @history:
 */
public class XN622090 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(coachAO.addCoach(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622090Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getRealName(),
            req.getPic(), req.getGender(), req.getLabel(), req.getAdvPic(),
            req.getDescription());
        StringValidater.validateNumber(req.getAge(), req.getDuration());
    }

}
