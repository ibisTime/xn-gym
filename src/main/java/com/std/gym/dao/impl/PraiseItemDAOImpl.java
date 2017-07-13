package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IPraiseItemDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.PraiseItem;

@Repository("praiseItemDAOImpl")
public class PraiseItemDAOImpl extends AMybatisTemplate implements
        IPraiseItemDAO {

    @Override
    public int insert(PraiseItem data) {
        return super.insert(NAMESPACE.concat("insert_praiseItem"), data);
    }

    @Override
    public int delete(PraiseItem data) {
        return super.delete(NAMESPACE.concat("delete_praiseItem"), data);
    }

    @Override
    public PraiseItem select(PraiseItem condition) {
        return super.select(NAMESPACE.concat("select_praiseItem"), condition,
            PraiseItem.class);
    }

    @Override
    public Long selectTotalCount(PraiseItem condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_praiseItem_count"), condition);
    }

    @Override
    public List<PraiseItem> selectList(PraiseItem condition) {
        return super.selectList(NAMESPACE.concat("select_praiseItem"),
            condition, PraiseItem.class);
    }

    @Override
    public List<PraiseItem> selectList(PraiseItem condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_praiseItem"), start,
            count, condition, PraiseItem.class);
    }

    @Override
    public int update(PraiseItem data) {
        return 0;
    }

}
