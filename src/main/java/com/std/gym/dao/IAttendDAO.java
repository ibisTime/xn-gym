package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.Attend;

//dao层 
public interface IAttendDAO extends IBaseDAO<Attend> {
    String NAMESPACE = IAttendDAO.class.getName().concat(".");

    int update(Attend data);

    int updateDatetime(Attend data);
}
