package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.OrgCourseOrder;



 
public interface IOrgCourseOrderBO extends IPaginableBO<OrgCourseOrder> {


	public boolean isOrgCourseOrderExist(String code);


	public String saveOrgCourseOrder(OrgCourseOrder data);


	public int removeOrgCourseOrder(String code);


	public int refreshOrgCourseOrder(OrgCourseOrder data);


	public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition);


	public OrgCourseOrder getOrgCourseOrder(String code);


}