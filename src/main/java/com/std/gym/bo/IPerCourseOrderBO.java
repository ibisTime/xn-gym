package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.PerCourseOrder;



 
public interface IPerCourseOrderBO extends IPaginableBO<PerCourseOrder> {


	public boolean isPerCourseOrderExist(String code);


	public String savePerCourseOrder(PerCourseOrder data);


	public int removePerCourseOrder(String code);


	public int refreshPerCourseOrder(PerCourseOrder data);


	public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition);


	public PerCourseOrder getPerCourseOrder(String code);


}