package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.domain.SizeData;
import com.std.gym.dto.res.XN622920Res;
import com.std.gym.dto.res.XN622921Res;

@Component
public interface IPerCourseOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String commitOrder(String applyUser, String address, String mobile,
            String perCourseCode, Integer quantity, String applyNote);

    public Object toPayOrder(String orderCode, String payType);

    public void paySuccess(String payGroup, String payCode, Long amount,
            String payType);

    public void receiverOrder(String orderCode, String updater, String remark);

    public void classBegin(String orderCode, String updater, String remark);

    public void toFullForm(String orderCode, List<SizeData> sizeDataList,
            String updater, String remark);

    public void classOver(String orderCode, String updater, String remark);

    public void userCancel(String orderCode, String updater, String remark);

    public void platCancel(String orderCode, String updater, String remark);

    public Paginable<PerCourseOrder> queryPerCourseOrderPage(int start,
            int limit, PerCourseOrder condition);

    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition);

    public PerCourseOrder getPerCourseOrder(String code);

    public void changeOrder();

    public XN622920Res totalUnfinish(String applyUser);

    public XN622921Res totalToComment(String userId);
}
