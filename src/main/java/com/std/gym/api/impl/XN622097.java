package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.dto.req.XN622097Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 列表查询私教
 * @author: asus 
 * @since: 2017年7月18日 下午1:43:32 
 * @history:
 */
public class XN622097 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622097Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Coach condition = new Coach();
        condition.setUserId(req.getUserId());
        condition.setStar(StringValidater.toInteger(req.getStar()));
        condition.setLabel(req.getLabel());
        condition.setStatus(req.getStatus());
        condition.setApprover(req.getApprover());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICoachAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return coachAO.queryCoachList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622097Req.class);
    }

}
