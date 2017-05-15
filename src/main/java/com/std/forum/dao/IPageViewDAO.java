package com.std.forum.dao;

import com.std.forum.dao.base.IBaseDAO;
import com.std.forum.domain.PageView;

public interface IPageViewDAO extends IBaseDAO<PageView> {
    String NAMESPACE = IPageViewDAO.class.getName().concat(".");

    public int update(PageView data);
}
