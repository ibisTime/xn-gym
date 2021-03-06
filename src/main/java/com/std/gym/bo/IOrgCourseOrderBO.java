package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.enums.EOrgCourseOrderStatus;

public interface IOrgCourseOrderBO extends IPaginableBO<OrgCourseOrder> {

    public boolean isOrgCourseOrderExist(String code);

    public void saveOrgCourseOrder(OrgCourseOrder data);

    public void payGroup(OrgCourseOrder order, String payGroup);

    public void removeOrgCourseOrder(String code);

    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition);

    public OrgCourseOrder getOrgCourseOrder(String code);

    public OrgCourseOrder getOrderPayGroup(String payGroup);

    public void paySuccess(OrgCourseOrder order, String payCode, Long amount,
            String payType);

    public void userCancel(OrgCourseOrder order, String applyUser);

    public void platCancel(OrgCourseOrder order, String updater, String remark);

    public void applyRefund(OrgCourseOrder order, Long penalty,
            String applyUser, String applyNote);

    public void approveRefund(OrgCourseOrder order, Long penalty,
            EOrgCourseOrderStatus status, String updater, String remark);

    public void finishOrder(OrgCourseOrder order);

    public void toComment(OrgCourseOrder order);

    public List<OrgCourseOrder> queryOrgCourseOrderList(String orgCourseCode,
            List<String> statusList);

    public void beginOrgCourseOrder(OrgCourseOrder orgCourseOrder);

    public Long getUnfinishCount(String applyUser, List<String> statusList);

}
