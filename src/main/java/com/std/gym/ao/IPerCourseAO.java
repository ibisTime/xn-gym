package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PerCourse;
import com.std.gym.dto.req.XN622100Req;
import com.std.gym.dto.req.XN622102Req;

@Component
public interface IPerCourseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPerCourse(XN622100Req req);

    public void dropPerCourse(String code);

    public void editPerCourse(XN622102Req req);

    public Paginable<PerCourse> queryPerCoursePage(int start, int limit,
            PerCourse condition);

    public List<PerCourse> queryPerCourseList(PerCourse condition);

    public PerCourse getPerCourse(String code);

}
