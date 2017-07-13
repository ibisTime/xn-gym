package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.OrgCourse;
import com.std.gym.exception.BizException;



 
@Service
public class OrgCourseAOImpl implements IOrgCourseAO {

	@Autowired
	private IOrgCourseBO orgCourseBO;

	@Override
	public String addOrgCourse(OrgCourse data) {
		return orgCourseBO.saveOrgCourse(data);
	}

	@Override
	public int editOrgCourse(OrgCourse data) {
		if (!orgCourseBO.isOrgCourseExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return orgCourseBO.refreshOrgCourse(data);
	}

	@Override
	public int dropOrgCourse(String code) {
		if (!orgCourseBO.isOrgCourseExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return orgCourseBO.removeOrgCourse(code);
	}

	@Override
	public Paginable<OrgCourse> queryOrgCoursePage(int start, int limit,
			OrgCourse condition) {
		return orgCourseBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<OrgCourse> queryOrgCourseList(OrgCourse condition) {
		return orgCourseBO.queryOrgCourseList(condition);
	}

	@Override
	public OrgCourse getOrgCourse(String code) {
		return orgCourseBO.getOrgCourse(code);
	}
}