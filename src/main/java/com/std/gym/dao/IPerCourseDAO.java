package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.PerCourse;

public interface IPerCourseDAO extends IBaseDAO<PerCourse> {
    String NAMESPACE = IPerCourseDAO.class.getName().concat(".");

    int update(PerCourse data);

    int updateStatus(PerCourse data);
}
