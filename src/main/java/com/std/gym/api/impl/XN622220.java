package com.std.gym.api.impl;

import com.std.gym.ao.IAttendAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622220Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 参加
 * @author: asus 
 * @since: 2017年9月7日 下午5:44:13 
 * @history:
 */
public class XN622220 extends AProcessor {
    private IAttendAO attendAO = SpringContextHolder.getBean(IAttendAO.class);

    private XN622220Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(attendAO.addAttend(req.getLoginName(),
            req.getLoginPwd(), req.getKind(), req.getActivityCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622220Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getLoginPwd(),
            req.getKind(), req.getActivityCode());
    }

}
