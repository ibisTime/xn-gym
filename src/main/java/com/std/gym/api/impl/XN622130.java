package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.dto.req.XN622130Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午8:27:34 
 * @history:
 */
public class XN622130 extends AProcessor {
    private IPerCourseOrderAO perCourseOrderAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622130Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setCourseName(req.getCourseName());
        condition.setMobile(req.getMobile());
        condition.setToUser(req.getToUser());
        condition.setPerCourseCode(req.getPerCourseCode());
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IOrgCourseOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return perCourseOrderAO
            .queryPerCourseOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622130Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
