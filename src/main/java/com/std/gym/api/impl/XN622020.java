package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.DateUtil;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Activity;
import com.std.gym.dto.req.XN622020Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询活动
 * @author: asus 
 * @since: 2017年7月17日 上午11:02:59 
 * @history:
 */
public class XN622020 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622020Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Activity condition = new Activity();
        condition.setLocation(req.getLocation());
        condition.setTitle(req.getTitle());
        condition.setHoldPlace(req.getHoldPlace());
        condition.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        condition.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IActivityAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return activityAO.queryActivityPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622020Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
