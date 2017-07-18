package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.OrgCourseOrder;

@Component
public interface IOrgCourseOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String commitOrder(String orgCourseCode, Integer quantity,
            String applyUser, String mobile, String applyNote);

    public Object toPayOrder(String orderCode, String payType);

    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType);

    public void userCancel(String orderCode, String applyUser);

    public void platCancel(String orderCode, String updater, String remark);

    public void applyRefund(String orderCode, String applyUser, String applyNote);

    public void approveRefund(String orderCode, String result, String updater,
            String remark);

    public void editOrgCourseOrder(OrgCourseOrder data);

    public Paginable<OrgCourseOrder> queryOrgCourseOrderPage(int start,
            int limit, OrgCourseOrder condition);

    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition);

    public OrgCourseOrder getOrgCourseOrder(String code);

}
