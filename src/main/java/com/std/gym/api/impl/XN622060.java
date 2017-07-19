package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.DateUtil;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.OrgCourse;
import com.std.gym.dto.req.XN622060Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询团课
 * @author: asus 
 * @since: 2017年7月17日 下午7:17:19 
 * @history:
 */
public class XN622060 extends AProcessor {
    private IOrgCourseAO orgCourseAO = SpringContextHolder
        .getBean(IOrgCourseAO.class);

    private XN622060Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OrgCourse condition = new OrgCourse();
        condition.setCoachUser(req.getCoachUser());
        condition.setName(req.getName());
        condition.setAddress(req.getAddress());
        condition.setLocation(req.getLocation());
        condition.setClassDatetime(DateUtil.strToDate(req.getClassDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setStatus(req.getStatus());
        condition.setContact(req.getContact());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IOrgCourseAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return orgCourseAO.queryOrgCoursePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622060Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
