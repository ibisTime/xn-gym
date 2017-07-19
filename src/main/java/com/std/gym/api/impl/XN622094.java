package com.std.gym.api.impl;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622094Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 详情查询私教(front)（带有私课和评论）
 * @author: asus 
 * @since: 2017年7月19日 上午11:15:23 
 * @history:
 */
public class XN622094 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622094Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return coachAO.getFrontCoach(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622094Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
