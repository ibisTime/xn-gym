package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.PraiseItem;



 
public interface IPraiseItemBO extends IPaginableBO<PraiseItem> {


	public boolean isPraiseItemExist(String code);


	public String savePraiseItem(PraiseItem data);


	public int removePraiseItem(String code);


	public int refreshPraiseItem(PraiseItem data);


	public List<PraiseItem> queryPraiseItemList(PraiseItem condition);


	public PraiseItem getPraiseItem(String code);


}