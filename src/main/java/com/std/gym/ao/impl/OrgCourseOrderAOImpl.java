package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.exception.BizException;



 
@Service
public class OrgCourseOrderAOImpl implements IOrgCourseOrderAO {

	@Autowired
	private IOrgCourseOrderBO orgCourseOrderBO;

	@Override
	public String addOrgCourseOrder(OrgCourseOrder data) {
		return orgCourseOrderBO.saveOrgCourseOrder(data);
	}

	@Override
	public int editOrgCourseOrder(OrgCourseOrder data) {
		if (!orgCourseOrderBO.isOrgCourseOrderExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return orgCourseOrderBO.refreshOrgCourseOrder(data);
	}

	@Override
	public int dropOrgCourseOrder(String code) {
		if (!orgCourseOrderBO.isOrgCourseOrderExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return orgCourseOrderBO.removeOrgCourseOrder(code);
	}

	@Override
	public Paginable<OrgCourseOrder> queryOrgCourseOrderPage(int start, int limit,
			OrgCourseOrder condition) {
		return orgCourseOrderBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition) {
		return orgCourseOrderBO.queryOrgCourseOrderList(condition);
	}

	@Override
	public OrgCourseOrder getOrgCourseOrder(String code) {
		return orgCourseOrderBO.getOrgCourseOrder(code);
	}
}