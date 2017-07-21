package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IPerCourseOrderDAO;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.enums.EPerCourseOrderStatus;
import com.std.gym.exception.BizException;

@Component
public class PerCourseOrderBOImpl extends PaginableBOImpl<PerCourseOrder>
        implements IPerCourseOrderBO {

    @Autowired
    private IPerCourseOrderDAO perCourseOrderDAO;

    @Override
    public boolean isPerCourseOrderExist(String code) {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setCode(code);
        if (perCourseOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void savePerCourseOrder(PerCourseOrder data) {
        perCourseOrderDAO.insert(data);
    }

    @Override
    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition) {
        return perCourseOrderDAO.selectList(condition);
    }

    @Override
    public PerCourseOrder getPerCourseOrder(String code) {
        PerCourseOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setCode(code);
            data = perCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public void payGroup(PerCourseOrder order, String payGroup) {
        order.setPayGroup(payGroup);
        perCourseOrderDAO.payGroup(order);
    }

    @Override
    public PerCourseOrder getOrderPayGroup(String payGroup) {
        PerCourseOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setPayGroup(payGroup);
            data = perCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "支付组号不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(PerCourseOrder order, String payCode, Long amount,
            String payType) {
        order.setStatus(EPerCourseOrderStatus.PAYSUCCESS.getCode());
        order.setPayType(payType);
        order.setPayAmount(amount);
        order.setPayCode(payCode);
        order.setPayDatetime(new Date());
        perCourseOrderDAO.paySuccess(order);
    }

    @Override
    public void receiverOrder(PerCourseOrder order, String updater,
            String remark) {
        order.setStatus(EPerCourseOrderStatus.RECEIVER_ORDER.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.receiverOrder(order);
    }

    @Override
    public void classBegin(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.HAVE_CLASS.getCode());
        order.setSkStartDatetime(new Date());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.classBegin(order);
    }

    @Override
    public void classOver(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.CLASS_OVER.getCode());
        order.setSkEndDatetime(new Date());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.classOver(order);
    }

    @Override
    public void userCancel(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.USER_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.receiverOrder(order);
    }

    @Override
    public void platCancel(PerCourseOrder order, String updater, String remark) {
        order.setStatus(EPerCourseOrderStatus.PLAT_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        perCourseOrderDAO.receiverOrder(order);
    }

    @Override
    public void finishOrder(PerCourseOrder perCourseOrder) {
        perCourseOrder.setStatus(EPerCourseOrderStatus.FINISH.getCode());
        perCourseOrderDAO.finishOrder(perCourseOrder);
    }

    @Override
    public Long getTotalCount(String perCourseCode, Date appointment,
            String skStartDatetime, String skEndDatetime) {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setPerCourseCode(perCourseCode);
        condition.setAppointDatetime(appointment);
        condition.setSkDatetime(skStartDatetime);
        condition.setXkDatetime(skEndDatetime);
        condition.setStatus(EPerCourseOrderStatus.PAYSUCCESS.getCode());
        return perCourseOrderDAO.selectTotalCount(condition);
    }
}
