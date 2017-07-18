package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.PerCourse;
import com.std.gym.dto.req.XN622100Req;
import com.std.gym.dto.req.XN622102Req;
import com.std.gym.enums.EPrefixCode;

@Service
public class PerCourseAOImpl implements IPerCourseAO {

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private ICoachBO coachBO;

    @Override
    public String addPerCourse(XN622100Req req) {
        coachBO.getCoach(req.getCoachCode());
        PerCourse data = new PerCourse();
        String code = OrderNoGenerater
            .generate(EPrefixCode.PERCOURSE.getCode());
        data.setCode(code);
        data.setName(req.getName());
        data.setSkCycle(req.getSkCycle());
        data.setSkStartDatetime(req.getSkStartDatetime());
        data.setSkEndDatetime(req.getSkEndDatetime());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setCoachCode(req.getCoachCode());
        perCourseBO.savePerCourse(data);
        return code;
    }

    @Override
    public void editPerCourse(XN622102Req req) {
        PerCourse data = perCourseBO.getPerCourse(req.getCode());
        data.setName(req.getName());
        data.setSkCycle(req.getSkCycle());
        data.setSkStartDatetime(req.getSkStartDatetime());
        data.setSkEndDatetime(req.getSkEndDatetime());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setPrice(StringValidater.toLong(req.getPrice()));
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
        if (condition.getClassDatetime() != null) {
            Integer week = DateUtil
                .getDayofweek(DateUtil.dateToStr(condition.getClassDatetime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
            condition.setClassDatetime(null);
            condition.setSkCycle(Integer.toString(week));
        }
        return perCourseBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PerCourse> queryPerCourseList(PerCourse condition) {
        if (condition.getClassDatetime() != null) {
            Integer week = DateUtil
                .getDayofweek(DateUtil.dateToStr(condition.getClassDatetime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
            condition.setClassDatetime(null);
            condition.setSkCycle(Integer.toString(week));
        }

        return perCourseBO.queryPerCourseList(condition);
    }

    @Override
    public PerCourse getPerCourse(String code) {
        return perCourseBO.getPerCourse(code);
    }
}
