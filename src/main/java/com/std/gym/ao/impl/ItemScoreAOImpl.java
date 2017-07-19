package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IItemScoreAO;
import com.std.gym.bo.IItemScoreBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.ItemScore;
import com.std.gym.exception.BizException;

@Service
public class ItemScoreAOImpl implements IItemScoreAO {

    @Autowired
    private IItemScoreBO itemScoreBO;

    @Override
    public String addItemScore(ItemScore data) {
        return itemScoreBO.saveItemScore(data);
    }

    @Override
    public int editItemScore(ItemScore data) {
        if (!itemScoreBO.isItemScoreExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return itemScoreBO.refreshItemScore(data);
    }

    @Override
    public int dropItemScore(String code) {
        if (!itemScoreBO.isItemScoreExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return itemScoreBO.removeItemScore(code);
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
