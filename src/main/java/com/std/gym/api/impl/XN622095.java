package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.dto.req.XN622095Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询私教(oss)
 * @author: asus 
 * @since: 2017年7月18日 下午1:43:32 
 * @history:
 */
public class XN622095 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622095Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Coach condition = new Coach();
        condition.setType(req.getType());
        condition.setUserId(req.getUserId());
        condition.setStar(StringValidater.toInteger(req.getStar()));
        condition.setLabel(req.getLabel());
        condition.setStatus(req.getStatus());
        condition.setLocation(req.getLocation());
        condition.setApprover(req.getApprover());
        condition.setMobile(req.getMobile());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICoachAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return coachAO.queryCoachPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622095Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
