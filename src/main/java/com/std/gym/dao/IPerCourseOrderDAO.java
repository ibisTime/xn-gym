package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.PerCourseOrder;

public interface IPerCourseOrderDAO extends IBaseDAO<PerCourseOrder> {
    String NAMESPACE = IPerCourseOrderDAO.class.getName().concat(".");

    int update(PerCourseOrder data);
}
