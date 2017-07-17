package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.OrgCourse;
import com.std.gym.dto.req.XN622050Req;
import com.std.gym.dto.req.XN622052Req;

@Component
public interface IOrgCourseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addOrgCourse(XN622050Req req);

    public void dropOrgCourse(String code);

    public void editOrgCourse(XN622052Req req);

    public void putOn(String code, String location, Integer orderNo,
            String updater, String remark);

    public void putOff(String code, String updater, String remark);

    public void stopSign(String code, String updater, String remark);

    public Paginable<OrgCourse> queryOrgCoursePage(int start, int limit,
            OrgCourse condition);

    public List<OrgCourse> queryOrgCourseList(OrgCourse condition);

    public OrgCourse getOrgCourse(String code);

}
