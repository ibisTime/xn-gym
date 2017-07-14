package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IItemScoreDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.ItemScore;

@Repository("praiseItemDAOImpl")
public class ItemScoreDAOImpl extends AMybatisTemplate implements
        IItemScoreDAO {

    @Override
    public int insert(ItemScore data) {
        return super.insert(NAMESPACE.concat("insert_praiseItem"), data);
    }

    @Override
    public int delete(ItemScore data) {
        return super.delete(NAMESPACE.concat("delete_praiseItem"), data);
    }

    @Override
    public ItemScore select(ItemScore condition) {
        return super.select(NAMESPACE.concat("select_praiseItem"), condition,
            ItemScore.class);
    }

    @Override
    public Long selectTotalCount(ItemScore condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_praiseItem_count"), condition);
    }

    @Override
    public List<ItemScore> selectList(ItemScore condition) {
        return super.selectList(NAMESPACE.concat("select_praiseItem"),
            condition, ItemScore.class);
    }

    @Override
    public List<ItemScore> selectList(ItemScore condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_praiseItem"), start,
            count, condition, ItemScore.class);
    }

    @Override
    public int update(ItemScore data) {
        return 0;
    }

}
