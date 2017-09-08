package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.Vote;

//dao层 
public interface IVoteDAO extends IBaseDAO<Vote> {
    String NAMESPACE = IVoteDAO.class.getName().concat(".");

    int update(Vote data);
}
