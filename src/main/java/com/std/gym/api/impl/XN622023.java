package com.std.gym.api.impl;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622023Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 新增投票活动
 * @author: asus 
 * @since: 2017年9月7日 下午5:22:56 
 * @history:
 */
public class XN622023 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622023Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(activityAO.addVote(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622023Req.class);
        StringValidater.validateBlank(req.getTitle(), req.getPic(),
            req.getDescription(), req.getStartDatetime(), req.getEndDatetime(),
            req.getUpdater());
    }
}
