package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.ItemScore;

@Component
public interface IItemScoreAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void addItemScore(ItemScore data);

    public Paginable<ItemScore> queryItemScorePage(int start, int limit,
            ItemScore condition);

    public List<ItemScore> queryItemScoreList(ItemScore condition);

    public ItemScore getItemScore(String code);

}
