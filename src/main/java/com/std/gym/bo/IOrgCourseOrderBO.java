package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.OrgCourseOrder;

public interface IOrgCourseOrderBO extends IPaginableBO<OrgCourseOrder> {

    public boolean isOrgCourseOrderExist(String code);

    public void saveOrgCourseOrder(OrgCourseOrder data);

    public void payGroup(OrgCourseOrder order, String payGroup);

    public void removeOrgCourseOrder(String code);

    public void refreshOrgCourseOrder(OrgCourseOrder data);

    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition);

    public OrgCourseOrder getOrgCourseOrder(String code);

    public OrgCourseOrder getOrderPayGroup(String payGroup);

    public void paySuccess(OrgCourseOrder order, String payCode, Long amount,
            String payType);

}
