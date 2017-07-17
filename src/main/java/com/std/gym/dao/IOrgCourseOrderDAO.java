package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.OrgCourseOrder;

public interface IOrgCourseOrderDAO extends IBaseDAO<OrgCourseOrder> {
    String NAMESPACE = IOrgCourseOrderDAO.class.getName().concat(".");

    int update(OrgCourseOrder data);

    int payGroup(OrgCourseOrder data);

    int paySuccess(OrgCourseOrder order);
}
