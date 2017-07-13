package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.OrgCourse;



 
public interface IOrgCourseBO extends IPaginableBO<OrgCourse> {


	public boolean isOrgCourseExist(String code);


	public String saveOrgCourse(OrgCourse data);


	public int removeOrgCourse(String code);


	public int refreshOrgCourse(OrgCourse data);


	public List<OrgCourse> queryOrgCourseList(OrgCourse condition);


	public OrgCourse getOrgCourse(String code);


}