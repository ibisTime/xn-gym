package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.OrgCourse;

@Component
public interface IOrgCourseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addOrgCourse(OrgCourse data);

    public int dropOrgCourse(String code);

    public int editOrgCourse(OrgCourse data);

    public Paginable<OrgCourse> queryOrgCoursePage(int start, int limit,
            OrgCourse condition);

    public List<OrgCourse> queryOrgCourseList(OrgCourse condition);

    public OrgCourse getOrgCourse(String code);

}
