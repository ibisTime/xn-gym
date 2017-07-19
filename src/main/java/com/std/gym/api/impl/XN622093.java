package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.ICoachAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.dto.req.XN622093Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询私教(front)（带有筛选）
 * @author: asus 
 * @since: 2017年7月18日 下午2:15:00 
 * @history:
 */
public class XN622093 extends AProcessor {
    private ICoachAO coachAO = SpringContextHolder.getBean(ICoachAO.class);

    private XN622093Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Coach condition = new Coach();
        condition.setStar(StringValidater.toInteger(req.getStar()));
        condition.setStatus(req.getStatus());
        condition.setSkCycle(req.getSkCycle());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICoachAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return coachAO.queryFrontCoachPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622093Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
