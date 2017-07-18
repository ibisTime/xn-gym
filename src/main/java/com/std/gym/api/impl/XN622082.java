package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.dto.req.XN622082Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询我的团课订单
 * @author: asus 
 * @since: 2017年7月18日 上午10:52:05 
 * @history:
 */
public class XN622082 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622082Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IOrgCourseOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return orgCourseOrderAO
            .queryOrgCourseOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622082Req.class);
        StringValidater.validateBlank(req.getApplyUser());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
