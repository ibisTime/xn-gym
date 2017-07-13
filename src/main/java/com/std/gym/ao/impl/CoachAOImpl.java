package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ICoachAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Coach;
import com.std.gym.exception.BizException;



 
@Service
public class CoachAOImpl implements ICoachAO {

	@Autowired
	private ICoachBO coachBO;

	@Override
	public String addCoach(Coach data) {
		return coachBO.saveCoach(data);
	}

	@Override
	public int editCoach(Coach data) {
		if (!coachBO.isCoachExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return coachBO.refreshCoach(data);
	}

	@Override
	public int dropCoach(String code) {
		if (!coachBO.isCoachExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return coachBO.removeCoach(code);
	}

	@Override
	public Paginable<Coach> queryCoachPage(int start, int limit,
			Coach condition) {
		return coachBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<Coach> queryCoachList(Coach condition) {
		return coachBO.queryCoachList(condition);
	}

	@Override
	public Coach getCoach(String code) {
		return coachBO.getCoach(code);
	}
}