package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.OrgCourse;

public interface IOrgCourseDAO extends IBaseDAO<OrgCourse> {
    String NAMESPACE = IOrgCourseDAO.class.getName().concat(".");

    int update(OrgCourse data);
}
