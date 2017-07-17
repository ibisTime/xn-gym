package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.DateUtil;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.ActivityOrder;
import com.std.gym.dto.req.XN622040Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询订单
 * @author: asus 
 * @since: 2017年7月17日 下午3:37:09 
 * @history:
 */
public class XN622040 extends AProcessor {
    private IActivityOrderAO activityOrderBO = SpringContextHolder
        .getBean(IActivityOrderAO.class);

    private XN622040Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ActivityOrder condition = new ActivityOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setMobile(req.getMobile());
        condition.setActivityCode(req.getActivityCode());
        condition.setActivityTitle(req.getActivityTitle());
        condition.setCreateBeginDatetime(DateUtil.strToDate(
            req.getApplyBeginDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateBeginDatetime(DateUtil.strToDate(
            req.getApplyBeginDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IActivityOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return activityOrderBO.queryActivityOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622040Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
