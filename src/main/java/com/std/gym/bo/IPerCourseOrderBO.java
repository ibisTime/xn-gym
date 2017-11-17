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

    public void userCancelPay(PerCourseOrder order, String updater,
            String remark);

    public void userCancelNoPay(PerCourseOrder order, String updater,
            String remark);

    public void platCancelPenalty(PerCourseOrder order, String updater,
            String remark);

    public void finishOrder(PerCourseOrder perCourseOrder);

    public Long getTotalCount(String perCourseCode, Date skStartDatetime,
            Date skEndDatetime);

    public Long getUnfinishCount(String type, String applyUser,
            List<String> statusList);

    public void toFullForm(PerCourseOrder order, String updater, String remark);

    public void updateIsSend(PerCourseOrder perCourseOrder);

    public void platTK(PerCourseOrder perCourseOrder);

    public void platCancel(PerCourseOrder order, String updater, String remark);

    public void platCancelHaveClass(PerCourseOrder order, String updater,
            String remark);
}
