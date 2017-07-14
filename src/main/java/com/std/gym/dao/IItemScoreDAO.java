package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.ItemScore;

public interface IItemScoreDAO extends IBaseDAO<ItemScore> {
    String NAMESPACE = IItemScoreDAO.class.getName().concat(".");

    int update(ItemScore data);
}
