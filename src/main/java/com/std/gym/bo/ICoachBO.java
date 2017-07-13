package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Coach;



 
public interface ICoachBO extends IPaginableBO<Coach> {


	public boolean isCoachExist(String code);


	public String saveCoach(Coach data);


	public int removeCoach(String code);


	public int refreshCoach(Coach data);


	public List<Coach> queryCoachList(Coach condition);


	public Coach getCoach(String code);


}