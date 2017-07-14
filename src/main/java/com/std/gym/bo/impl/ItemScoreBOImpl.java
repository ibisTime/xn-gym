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
    private IItemScoreDAO praiseItemDAO;

    @Override
    public boolean isPraiseItemExist(String code) {
        ItemScore condition = new ItemScore();
        condition.setCode(code);
        if (praiseItemDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String savePraiseItem(ItemScore data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.PRAISEITEM.getCode());
            data.setCode(code);
            praiseItemDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removePraiseItem(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ItemScore data = new ItemScore();
            data.setCode(code);
            count = praiseItemDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPraiseItem(ItemScore data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = praiseItemDAO.update(data);
        }
        return count;
    }

    @Override
    public List<ItemScore> queryPraiseItemList(ItemScore condition) {
        return praiseItemDAO.selectList(condition);
    }

    @Override
    public ItemScore getPraiseItem(String code) {
        ItemScore data = null;
        if (StringUtils.isNotBlank(code)) {
            ItemScore condition = new ItemScore();
            condition.setCode(code);
            data = praiseItemDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
