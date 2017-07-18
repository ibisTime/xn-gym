package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.DateUtil;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.dto.req.XN622080Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询团课订单
 * @author: asus 
 * @since: 2017年7月18日 上午10:25:31 
 * @history:
 */
public class XN622080 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622080Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setMobile(req.getMobile());
        condition.setOrgCourseCode(req.getOrgCourseCode());
        condition.setApplyBeginDatetime(DateUtil.strToDate(
            req.getApplyBeginDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setApplyEndDatetime(DateUtil.strToDate(
            req.getApplyEndDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
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
        req = JsonUtil.json2Bean(inputparams, XN622080Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
