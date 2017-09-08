package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IActivityAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.DateUtil;
import com.std.gym.common.JsonUtil;
import com.std.gym.domain.Activity;
import com.std.gym.dto.req.XN622022Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 查询活动
 * @author: asus 
 * @since: 2017年7月17日 上午11:02:59 
 * @history:
 */
public class XN622022 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN622022Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Activity condition = new Activity();
        condition.setType(req.getType());
        condition.setLocation(req.getLocation());
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
        return activityAO.queryActivityList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622022Req.class);
    }

}
