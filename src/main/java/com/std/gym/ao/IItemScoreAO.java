package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.ItemScore;

@Component
public interface IItemScoreAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPraiseItem(ItemScore data);

    public int dropPraiseItem(String code);

    public int editPraiseItem(ItemScore data);

    public Paginable<ItemScore> queryPraiseItemPage(int start, int limit,
            ItemScore condition);

    public List<ItemScore> queryPraiseItemList(ItemScore condition);

    public ItemScore getPraiseItem(String code);

}
