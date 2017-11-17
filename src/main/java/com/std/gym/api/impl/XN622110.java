package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.PerCourse;
import com.std.gym.dto.req.XN622110Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询私课
 * @author: asus 
 * @since: 2017年7月18日 下午4:37:01 
 * @history:
 */
public class XN622110 extends AProcessor {
    private IPerCourseAO perCourseAO = SpringContextHolder
        .getBean(IPerCourseAO.class);

    private XN622110Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PerCourse condition = new PerCourse();
        condition.setCoachCode(req.getCoachCode());
        condition.setClassDatetime(req.getClassDatetime());
        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPerCourseAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return perCourseAO.queryPerCoursePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622110Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
