package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.ItemScore;



 
public interface IItemScoreBO extends IPaginableBO<ItemScore> {


	public boolean isPraiseItemExist(String code);


	public String savePraiseItem(ItemScore data);


	public int removePraiseItem(String code);


	public int refreshPraiseItem(ItemScore data);


	public List<ItemScore> queryPraiseItemList(ItemScore condition);


	public ItemScore getPraiseItem(String code);


}