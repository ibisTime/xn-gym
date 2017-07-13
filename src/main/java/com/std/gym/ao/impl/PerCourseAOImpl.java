package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IPerCourseAO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PerCourse;
import com.std.gym.exception.BizException;



 
@Service
public class PerCourseAOImpl implements IPerCourseAO {

	@Autowired
	private IPerCourseBO perCourseBO;

	@Override
	public String addPerCourse(PerCourse data) {
		return perCourseBO.savePerCourse(data);
	}

	@Override
	public int editPerCourse(PerCourse data) {
		if (!perCourseBO.isPerCourseExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return perCourseBO.refreshPerCourse(data);
	}

	@Override
	public int dropPerCourse(String code) {
		if (!perCourseBO.isPerCourseExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return perCourseBO.removePerCourse(code);
	}

	@Override
	public Paginable<PerCourse> queryPerCoursePage(int start, int limit,
			PerCourse condition) {
		return perCourseBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<PerCourse> queryPerCourseList(PerCourse condition) {
		return perCourseBO.queryPerCourseList(condition);
	}

	@Override
	public PerCourse getPerCourse(String code) {
		return perCourseBO.getPerCourse(code);
	}
}