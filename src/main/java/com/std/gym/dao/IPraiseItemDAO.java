package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.PraiseItem;

public interface IPraiseItemDAO extends IBaseDAO<PraiseItem> {
    String NAMESPACE = IPraiseItemDAO.class.getName().concat(".");

    int update(PraiseItem data);
}
