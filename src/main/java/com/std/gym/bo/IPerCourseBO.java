package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.PerCourse;



 
public interface IPerCourseBO extends IPaginableBO<PerCourse> {


	public boolean isPerCourseExist(String code);


	public String savePerCourse(PerCourse data);


	public int removePerCourse(String code);


	public int refreshPerCourse(PerCourse data);


	public List<PerCourse> queryPerCourseList(PerCourse condition);


	public PerCourse getPerCourse(String code);


}