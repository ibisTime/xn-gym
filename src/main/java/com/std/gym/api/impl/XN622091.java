package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622091Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 修改私教信息
 * @author: asus 
 * @since: 2017年7月18日 下午12:31:26 
 * @history:
 */
public class XN622091 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622091Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        coachAO.editCoach(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622091Req.class);
        StringValidater.validateBlank(req.getCode(), req.getPic(),
            req.getLabel(), req.getAdvPic(), req.getDescription());
        StringValidater.validateNumber(req.getAge(), req.getDuration());
    }

}
