package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IItemScoreAO;
import com.std.gym.bo.IItemScoreBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.ItemScore;

@Service
public class ItemScoreAOImpl implements IItemScoreAO {

    @Autowired
    private IItemScoreBO itemScoreBO;

    @Override
    public void addItemScore(ItemScore data) {
        itemScoreBO.saveItemScore(data);
    }

    @Override
    public Paginable<ItemScore> queryItemScorePage(int start, int limit,
            ItemScore condition) {
        return itemScoreBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ItemScore> queryItemScoreList(ItemScore condition) {
        return itemScoreBO.queryItemScoreList(condition);
    }

    @Override
    public ItemScore getItemScore(String code) {
        return itemScoreBO.getItemScore(code);
    }
}
