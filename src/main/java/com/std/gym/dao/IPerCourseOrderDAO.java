package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.PerCourseOrder;

public interface IPerCourseOrderDAO extends IBaseDAO<PerCourseOrder> {
    String NAMESPACE = IPerCourseOrderDAO.class.getName().concat(".");

    int update(PerCourseOrder data);

    int payGroup(PerCourseOrder data);

    int paySuccess(PerCourseOrder data);

    int receiverOrder(PerCourseOrder data);

    int classBegin(PerCourseOrder data);

    int classOver(PerCourseOrder data);

    int finishOrder(PerCourseOrder data);

    int platCancelOrder(PerCourseOrder data);

    int userCancelOrder(PerCourseOrder data);

    int updateIsSend(PerCourseOrder order);
}
