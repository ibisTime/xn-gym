package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.ItemScore;

public interface IItemScoreBO extends IPaginableBO<ItemScore> {

    public void saveItemScore(ItemScore data);

    public int removeItemScore(String code);

    public int refreshItemScore(ItemScore data);

    public List<ItemScore> queryItemScoreList(ItemScore condition);

    public ItemScore getItemScore(String code);

    public List<ItemScore> queryItemScoreList(String commentCode);

}
