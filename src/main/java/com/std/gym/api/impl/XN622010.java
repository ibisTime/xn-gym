package com.std.gym.api.impl;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622010Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 新增活动
 * @author: asus 
 * @since: 2017年7月17日 上午9:38:43 
 * @history:
 */
public class XN622010 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622010Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(activityAO.addNewActivity(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622010Req.class);
        StringValidater.validateBlank(req.getTitle(), req.getPic(),
            req.getAdvPic(), req.getSlogan(), req.getDescription(),
            req.getHoldPlace(), req.getContact(), req.getStartDatetime(),
            req.getEndDatetime(), req.getUpdater());
        StringValidater.validateAmount(req.getAmount());
        StringValidater.validateNumber(req.getTotalNum());
    }
}
