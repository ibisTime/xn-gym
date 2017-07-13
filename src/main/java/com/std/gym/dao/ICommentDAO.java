package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.Comment;

public interface ICommentDAO extends IBaseDAO<Comment> {
    String NAMESPACE = ICommentDAO.class.getName().concat(".");

    int update(Comment data);
}
