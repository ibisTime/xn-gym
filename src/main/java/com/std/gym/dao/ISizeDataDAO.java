package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.SizeData;

//dao层 
public interface ISizeDataDAO extends IBaseDAO<SizeData> {
    String NAMESPACE = ISizeDataDAO.class.getName().concat(".");
}
