package com.std.gym.api.impl;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622012Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 修改活动
 * @author: asus 
 * @since: 2017年7月17日 上午10:15:46 
 * @history:
 */
public class XN622012 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityAO.modifyActivity(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622012Req.class);
        StringValidater.validateBlank(req.getCode(), req.getTitle(),
            req.getPic(), req.getAdvPic(), req.getSlogan(),
            req.getDescription(), req.getHoldPlace(), req.getContact(),
            req.getStartDatetime(), req.getEndDatetime(), req.getUpdater());
        StringValidater.validateAmount(req.getAmount());
        StringValidater.validateNumber(req.getTotalNum());

    }

}
