package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PerCourse;

@Component
public interface IPerCourseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPerCourse(PerCourse data);

    public int dropPerCourse(String code);

    public int editPerCourse(PerCourse data);

    public Paginable<PerCourse> queryPerCoursePage(int start, int limit,
            PerCourse condition);

    public List<PerCourse> queryPerCourseList(PerCourse condition);

    public PerCourse getPerCourse(String code);

}
