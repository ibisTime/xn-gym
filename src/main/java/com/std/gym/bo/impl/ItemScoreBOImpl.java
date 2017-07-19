package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IItemScoreBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IItemScoreDAO;
import com.std.gym.domain.ItemScore;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Component
public class ItemScoreBOImpl extends PaginableBOImpl<ItemScore> implements
        IItemScoreBO {

    @Autowired
    private IItemScoreDAO itemScoreDAO;

    @Override
    public boolean isItemScoreExist(String code) {
        ItemScore condition = new ItemScore();
        condition.setCode(code);
        if (itemScoreDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveItemScore(ItemScore data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.PRAISEITEM.getCode());
            data.setCode(code);
            itemScoreDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeItemScore(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ItemScore data = new ItemScore();
            data.setCode(code);
            count = itemScoreDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshItemScore(ItemScore data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
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
            condition.setCode(code);
            data = itemScoreDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
