package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.Coach;

public interface ICoachDAO extends IBaseDAO<Coach> {
    String NAMESPACE = ICoachDAO.class.getName().concat(".");

    int update(Coach data);
}
