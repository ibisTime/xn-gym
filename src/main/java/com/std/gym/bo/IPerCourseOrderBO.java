package com.std.gym.bo;

import java.util.Date;
import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.PerCourseOrder;

public interface IPerCourseOrderBO extends IPaginableBO<PerCourseOrder> {

    public boolean isPerCourseOrderExist(String code);

    public void savePerCourseOrder(PerCourseOrder data);

    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition);

    public PerCourseOrder getPerCourseOrder(String code);

    public void payGroup(PerCourseOrder order, String payGroup);

    public PerCourseOrder getOrderPayGroup(String payGroup);

    public void paySuccess(PerCourseOrder order, String payCode, Long amount,
            String payType);

    public void receiverOrder(PerCourseOrder order, String updater,
            String remark);

    public void classBegin(PerCourseOrder order, String updater, String remark);

    public void classOver(PerCourseOrder order, String updater, String remark);

    public void userCancel(PerCourseOrder order, Long penalty, String updater,
            String remark);

    public void platCancel(PerCourseOrder order, String updater, String remark);

    public void finishOrder(PerCourseOrder perCourseOrder);

    public Long getTotalCount(String perCourseCode, Date appointment,
            String skStartDatetime, String skEndDatetime);

    public Long getUnfinishCount(String applyUser, List<String> statusList);

    public void toFullForm(PerCourseOrder order, String updater, String remark);

}
