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
	private IItemScoreBO praiseItemBO;

	@Override
	public String addPraiseItem(ItemScore data) {
		return praiseItemBO.savePraiseItem(data);
	}

	@Override
	public int editPraiseItem(ItemScore data) {
		if (!praiseItemBO.isPraiseItemExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return praiseItemBO.refreshPraiseItem(data);
	}

	@Override
	public int dropPraiseItem(String code) {
		if (!praiseItemBO.isPraiseItemExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return praiseItemBO.removePraiseItem(code);
	}

	@Override
	public Paginable<ItemScore> queryPraiseItemPage(int start, int limit,
			ItemScore condition) {
		return praiseItemBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<ItemScore> queryPraiseItemList(ItemScore condition) {
		return praiseItemBO.queryPraiseItemList(condition);
	}

	@Override
	public ItemScore getPraiseItem(String code) {
		return praiseItemBO.getPraiseItem(code);
	}
}