package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.PerCourse;
import com.std.gym.dto.req.XN622100Req;
import com.std.gym.dto.req.XN622102Req;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Service
public class PerCourseAOImpl implements IPerCourseAO {

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IPerCourseOrderBO perCourseOrderBO;

    @Override
    public String addPerCourse(XN622100Req req) {
        coachBO.getCoach(req.getCoachCode());

        if (DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1).before(new Date())) {
            throw new BizException("xn0000", "上课时间必须晚于现在时间");
        }
        if (DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1).after(
            DateUtil.strToDate(req.getSkEndDatetime(),
                DateUtil.DATA_TIME_PATTERN_1))) {
            throw new BizException("xn0000", "上课时间不能晚于下课时间");
        }
        PerCourse data = new PerCourse();
        String code = OrderNoGenerater
            .generate(EPrefixCode.PERCOURSE.getCode());
        data.setCode(code);
        data.setSkCycle(StringValidater.toInteger(req.getSkCycle()));
        data.setSkStartDatetime(DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        data.setSkEndDatetime(DateUtil.strToDate(req.getSkEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        data.setAddress(req.getAddress());
        data.setTotalNum(StringValidater.toInteger(req.getTotalNum()));
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setCoachCode(req.getCoachCode());
        data.setStatus(EBoolean.NO.getCode());
        perCourseBO.savePerCourse(data);
        return code;
    }

    @Override
    public void editPerCourse(XN622102Req req) {
        if (DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1).before(new Date())) {
            throw new BizException("xn0000", "上课时间必须晚于现在时间");
        }
        if (DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1).after(
            DateUtil.strToDate(req.getSkEndDatetime(),
                DateUtil.DATA_TIME_PATTERN_1))) {
            throw new BizException("xn0000", "上课时间不能晚于下课时间");
        }
        PerCourse data = perCourseBO.getPerCourse(req.getCode());
        data.setSkCycle(StringValidater.toInteger(req.getSkCycle()));
        data.setSkStartDatetime(DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        data.setSkEndDatetime(DateUtil.strToDate(req.getSkEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        data.setAddress(req.getAddress());
        data.setTotalNum(StringValidater.toInteger(req.getTotalNum()));
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setStatus(EBoolean.NO.getCode());
        perCourseBO.refreshPerCourse(data);
    }

    @Override
    public void dropPerCourse(String code) {
        PerCourse perCourse = perCourseBO.getPerCourse(code);
        perCourseBO.removePerCourse(perCourse);
    }

    @Override
    public Paginable<PerCourse> queryPerCoursePage(int start, int limit,
            PerCourse condition) {
        if (StringUtils.isNotEmpty(condition.getClassDatetime())) {
            condition.setClassDatetimeStart(DateUtil.getStartDatetime(condition
                .getClassDatetime()));
            condition.setClassDatetimeEnd(DateUtil.getEndDatetime(condition
                .getClassDatetime()));
            condition.setClassDatetime(null);
        }
        return perCourseBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PerCourse> queryPerCourseList(PerCourse condition) {
        if (StringUtils.isNotEmpty(condition.getClassDatetime())) {
            condition.setClassDatetimeStart(DateUtil.getStartDatetime(condition
                .getClassDatetime()));
            condition.setClassDatetimeEnd(DateUtil.getEndDatetime(condition
                .getClassDatetime()));
            condition.setClassDatetime(null);
        }
        return perCourseBO.queryPerCourseList(condition);
    }

    @Override
    public PerCourse getPerCourse(String code) {
        return perCourseBO.getPerCourse(code);
    }
}
