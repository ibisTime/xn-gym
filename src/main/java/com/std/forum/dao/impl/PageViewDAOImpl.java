package com.std.forum.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.forum.dao.IPageViewDAO;
import com.std.forum.dao.base.support.AMybatisTemplate;
import com.std.forum.domain.PageView;

@Repository("pageViewDAOImpl")
public class PageViewDAOImpl extends AMybatisTemplate implements IPageViewDAO {

    @Override
    public int insert(PageView data) {
        return super.insert(NAMESPACE.concat("insert_pageView"), data);
    }

    @Override
    public int delete(PageView data) {
        return super.delete(NAMESPACE.concat("delete_pageView"), data);
    }

    @Override
    public PageView select(PageView condition) {
        return super.select(NAMESPACE.concat("select_pageView"), condition,
            PageView.class);
    }

    @Override
    public Long selectTotalCount(PageView condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_pageView_count"), condition);
    }

    @Override
    public List<PageView> selectList(PageView condition) {
        return super.selectList(NAMESPACE.concat("select_pageView"), condition,
            PageView.class);
    }

    @Override
    public List<PageView> selectList(PageView condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_pageView"), start,
            count, condition, PageView.class);
    }

    @Override
    public int update(PageView data) {
        return super.update(NAMESPACE.concat("update_pageView"), data);
    }

}
