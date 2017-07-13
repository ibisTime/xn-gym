package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.OrgCourseOrder;

@Component
public interface IOrgCourseOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addOrgCourseOrder(OrgCourseOrder data);

    public int dropOrgCourseOrder(String code);

    public int editOrgCourseOrder(OrgCourseOrder data);

    public Paginable<OrgCourseOrder> queryOrgCourseOrderPage(int start,
            int limit, OrgCourseOrder condition);

    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition);

    public OrgCourseOrder getOrgCourseOrder(String code);

}
