package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.OrgCourse;

public interface IOrgCourseBO extends IPaginableBO<OrgCourse> {

    public boolean isOrgCourseExist(String code);

    public void saveOrgCourse(OrgCourse data);

    public void removeOrgCourse(String code);

    public void refreshOrgCourse(OrgCourse data);

    public void putOn(OrgCourse orgCourse, String location, Integer orderNo,
            String updater, String remark);

    public void putOff(OrgCourse orgCourse, String updater, String remark);

    public void stopSign(OrgCourse orgCourse, String updater, String remark);

    public List<OrgCourse> queryOrgCourseList(OrgCourse condition);

    public OrgCourse getOrgCourse(String code);

    public void addSignNum(OrgCourse orgCourse, Integer quantity);

}
