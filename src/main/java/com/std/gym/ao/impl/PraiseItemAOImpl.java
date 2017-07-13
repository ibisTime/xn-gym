package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IPraiseItemAO;
import com.std.gym.bo.IPraiseItemBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PraiseItem;
import com.std.gym.exception.BizException;



 
@Service
public class PraiseItemAOImpl implements IPraiseItemAO {

	@Autowired
	private IPraiseItemBO praiseItemBO;

	@Override
	public String addPraiseItem(PraiseItem data) {
		return praiseItemBO.savePraiseItem(data);
	}

	@Override
	public int editPraiseItem(PraiseItem data) {
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
	public Paginable<PraiseItem> queryPraiseItemPage(int start, int limit,
			PraiseItem condition) {
		return praiseItemBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<PraiseItem> queryPraiseItemList(PraiseItem condition) {
		return praiseItemBO.queryPraiseItemList(condition);
	}

	@Override
	public PraiseItem getPraiseItem(String code) {
		return praiseItemBO.getPraiseItem(code);
	}
}