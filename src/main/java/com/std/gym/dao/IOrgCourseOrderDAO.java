package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.OrgCourseOrder;

public interface IOrgCourseOrderDAO extends IBaseDAO<OrgCourseOrder> {
    String NAMESPACE = IOrgCourseOrderDAO.class.getName().concat(".");

    int update(OrgCourseOrder data);

    int payGroup(OrgCourseOrder data);

    int paySuccess(OrgCourseOrder data);

    int userCancel(OrgCourseOrder data);

    int platCancel(OrgCourseOrder data);

    int applyRefund(OrgCourseOrder data);

    int approveRefund(OrgCourseOrder data);

    int finishOrder(OrgCourseOrder data);
}
