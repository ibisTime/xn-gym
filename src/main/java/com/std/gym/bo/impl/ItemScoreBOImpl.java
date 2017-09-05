package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IItemScoreBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IItemScoreDAO;
import com.std.gym.domain.ItemScore;
import com.std.gym.exception.BizException;

@Component
public class ItemScoreBOImpl extends PaginableBOImpl<ItemScore> implements
        IItemScoreBO {

    @Autowired
    private IItemScoreDAO itemScoreDAO;

    @Override
    public void saveItemScore(ItemScore data) {
        itemScoreDAO.insert(data);
    }

    @Override
    public int removeItemScore(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ItemScore data = new ItemScore();
            data.setId(code);
            count = itemScoreDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshItemScore(ItemScore data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getId())) {
            count = itemScoreDAO.update(data);
        }
        return count;
    }

    @Override
    public List<ItemScore> queryItemScoreList(ItemScore condition) {
        return itemScoreDAO.selectList(condition);
    }

    @Override
    public ItemScore getItemScore(String code) {
        ItemScore data = null;
        if (StringUtils.isNotBlank(code)) {
            ItemScore condition = new ItemScore();
            condition.setId(code);
            data = itemScoreDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<ItemScore> queryItemScoreList(String commentCode) {
        ItemScore condition = new ItemScore();
        condition.setCommentCode(commentCode);
        return itemScoreDAO.selectList(condition);
    }
}
