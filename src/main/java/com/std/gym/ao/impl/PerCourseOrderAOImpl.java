package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.exception.BizException;



 
@Service
public class PerCourseOrderAOImpl implements IPerCourseOrderAO {

	@Autowired
	private IPerCourseOrderBO perCourseOrderBO;

	@Override
	public String addPerCourseOrder(PerCourseOrder data) {
		return perCourseOrderBO.savePerCourseOrder(data);
	}

	@Override
	public int editPerCourseOrder(PerCourseOrder data) {
		if (!perCourseOrderBO.isPerCourseOrderExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return perCourseOrderBO.refreshPerCourseOrder(data);
	}

	@Override
	public int dropPerCourseOrder(String code) {
		if (!perCourseOrderBO.isPerCourseOrderExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return perCourseOrderBO.removePerCourseOrder(code);
	}

	@Override
	public Paginable<PerCourseOrder> queryPerCourseOrderPage(int start, int limit,
			PerCourseOrder condition) {
		return perCourseOrderBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition) {
		return perCourseOrderBO.queryPerCourseOrderList(condition);
	}

	@Override
	public PerCourseOrder getPerCourseOrder(String code) {
		return perCourseOrderBO.getPerCourseOrder(code);
	}
}