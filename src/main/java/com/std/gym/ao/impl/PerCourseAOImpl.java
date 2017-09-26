package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

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
        PerCourse data = new PerCourse();
        String code = OrderNoGenerater
            .generate(EPrefixCode.PERCOURSE.getCode());
        data.setCode(code);
        data.setSkCycle(StringValidater.toInteger(req.getSkCycle()));
        data.setSkStartDatetime(req.getSkStartDatetime());
        data.setSkEndDatetime(req.getSkEndDatetime());
        data.setAddress(req.getAddress());
        data.setTotalNum(StringValidater.toInteger(req.getTotalNum()));
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
        data.setSkCycle(StringValidater.toInteger(req.getSkCycle()));
        data.setSkStartDatetime(req.getSkStartDatetime());
        data.setSkEndDatetime(req.getSkEndDatetime());
        data.setAddress(req.getAddress());
        data.setTotalNum(StringValidater.toInteger(req.getTotalNum()));
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
            condition.setSkCycle(week);
        }
        Paginable<PerCourse> page = perCourseBO.getPaginable(start, limit,
            condition);
        List<PerCourse> list = page.getList();
        this.fullPerCourse(list);
        return page;
    }

    private void fullPerCourse(List<PerCourse> list) {
        for (PerCourse perCourse : list) {
            perCourse.setIsAppoint(EBoolean.NO.getCode());
            // 计算今天是周几
            Integer weekDay = DateUtil.getDayofweek(DateUtil.dateToStr(
                new Date(), DateUtil.FRONT_DATE_FORMAT_STRING));
            Integer skDays = 0;// 距离下次上课天数
            Integer skCycle = perCourse.getSkCycle();
            if (skCycle < weekDay) {// 下周预约
                skDays =7-(weekDay - skCycle);
            } else if (skCycle > weekDay) {// 本周预约
                skDays = skCycle - weekDay;
            }
            Date appointment = DateUtil.getRelativeDate(
                DateUtil.getTodayStart(), 24 * 3600 * skDays);
            Date appointDatetime = DateUtil.strToDate(
                DateUtil.dateToStr(appointment,
                    DateUtil.FRONT_DATE_FORMAT_STRING)
                        + " "
                        + perCourse.getSkStartDatetime(),
                DateUtil.DATA_TIME_PATTERN_1);
            if (appointDatetime.before(new Date())) {
                appointment = DateUtil.getRelativeDate(appointment,
                    24 * 3600 * 7);
            }
            Long skCount = perCourseOrderBO.getTotalCount(perCourse.getCode(),
                appointment, perCourse.getSkStartDatetime(),
                perCourse.getSkEndDatetime());
            if (skCount > 0) {
                perCourse.setIsAppoint(EBoolean.YES.getCode());
            }
        }
    }

    @Override
    public List<PerCourse> queryPerCourseList(PerCourse condition) {
        if (condition.getClassDatetime() != null) {
            Integer week = DateUtil
                .getDayofweek(DateUtil.dateToStr(condition.getClassDatetime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
            condition.setClassDatetime(null);
            condition.setSkCycle(week);
        }
        List<PerCourse> list = perCourseBO.queryPerCourseList(condition);
        this.fullPerCourse(list);
        return list;
    }

    @Override
    public PerCourse getPerCourse(String code) {
        return perCourseBO.getPerCourse(code);
    }
}
