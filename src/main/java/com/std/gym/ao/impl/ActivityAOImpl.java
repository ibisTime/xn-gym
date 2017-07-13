package com.std.gym.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IActivityAO;
import com.std.gym.bo.IActivityBO;
import com.std.gym.bo.IActivityOrderBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Activity;
import com.std.gym.domain.ActivityOrder;
import com.std.gym.enums.EActivityOrderStatus;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

/**
 * 
 * @author: shan 
 * @since: 2016年12月12日 上午10:43:35 
 * @history:
 */
@Service
public class ActivityAOImpl implements IActivityAO {
    static Logger logger = Logger.getLogger(ActivityAOImpl.class);

    @Autowired
    IActivityBO activityBO;

    @Autowired
    IActivityOrderBO orderBO;

    @Override
    public String addNewActivity(XN660000Req req) {
        Activity data = new Activity();
        String code = OrderNoGenerater.generate(EPrefixCode.Activity.getCode());
        data.setCode(code);
        data.setTitle(req.getTitle());
        data.setPic1(req.getPic1());
        data.setFee(StringValidater.toLong(req.getFee()));
        data.setDescription(req.getDescription());
        data.setHoldPlace(req.getHoldPlace());
        data.setOrderNo(0);
        data.setBeginDatetime(DateUtil.strToDate(req.getBeginDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setSingleNum(req.getSingleNum());
        data.setLimitNum(StringValidater.toInteger(req.getLimitNum()));
        data.setStatus(EActivityStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setCompanyCode(req.getCompanyCode());
        activityBO.saveActivity(data);
        return code;
    }

    @Override
    public void modifyActivity(XN660002Req req) {
        Activity activity = activityBO.getActivity(req.getCode());
        if (EActivityStatus.END.getCode().equals(activity.getStatus())
                || EActivityStatus.ONLINE.getCode()
                    .equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动已上线/结束,不可编辑");
        }

        Activity data = new Activity();
        data.setCode(req.getCode());
        data.setTitle(req.getTitle());
        data.setPic1(req.getPic1());
        data.setFee(StringValidater.toLong(req.getFee()));
        data.setDescription(req.getDescription());
        data.setHoldPlace(req.getHoldPlace());
        data.setBeginDatetime(DateUtil.strToDate(req.getBeginDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setOrderNo(0);
        data.setSingleNum(req.getSingleNum());
        data.setLimitNum(StringValidater.toInteger(req.getLimitNum()));
        data.setStatus(activity.getStatus());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        activityBO.modifyActivity(data);
    }

    @Override
    public void shelves(String code, String updater, String remark) {
        Activity activity = activityBO.getActivity(code);
        if (EActivityStatus.ONLINE.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动已经上线,无需重复上线");
        }
        if (EActivityStatus.END.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动已经结束,无可上线");
        }
        activityBO.shelves(activity, updater, remark);
    }

    @Override
    public void downActivity(String code, String updater, String remark) {
        Activity activity = activityBO.getActivity(code);
        if (EActivityStatus.DRAFT.getCode().equals(activity.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(
                    activity.getStatus())
                || EActivityStatus.END.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动不处于可下架状态,请核对后在操作");
        }
        activityBO.downActivity(activity, updater, remark);
    }

    @Override
    public void scanActivity(String code) {
        Activity activity = activityBO.getActivity(code);
        activityBO.scanActivity(activity, null);
    }

    @Override
    public Paginable<Activity> queryActivityPage(int start, int limit,
            Activity condition, String userId) {
        Paginable<Activity> page = activityBO.getPaginable(start, limit,
            condition);
        List<Activity> activityList = page.getList();
        for (Activity activity : activityList) {

        }
        return page;
    }

    @Override
    public List<Activity> queryActivityList(Activity condition, String userId) {
        List<Activity> activityList = activityBO.queryActivityList(condition);
        for (Activity activity : activityList) {
            List<String> statusList = new ArrayList<String>();
            statusList.add(EActivityOrderStatus.PAYSUCCESS.getCode());
            statusList.add(EActivityOrderStatus.NOTPAY.getCode());
            List<ActivityOrder> orderList = orderBO.queryOrderList(userId,
                activity.getCode(), statusList);

        }
        return activityList;
    }

    @Override
    public Activity getActivity(String code, String userId) {
        Activity activity = activityBO.getActivity(code);
        List<String> statusList = new ArrayList<String>();
        statusList.add(EActivityOrderStatus.PAYSUCCESS.getCode());
        statusList.add(EActivityOrderStatus.NOTPAY.getCode());
        List<ActivityOrder> orderList = orderBO.queryOrderList(userId,
            activity.getCode(), statusList);
        if (CollectionUtils.isNotEmpty(orderList)) {
        }
        return activity;
    }

    @Override
    public void changeOrder() {
        logger.info("***************开始扫描待活动，过期取消***************");
        Activity condition = new Activity();
        condition.setStatus(EActivityStatus.ONLINE.getCode());
        List<Activity> activityList = activityBO.queryActivityList(condition);
        if (CollectionUtils.isNotEmpty(activityList)) {
            for (Activity activity : activityList) {
                if (activity.getStartDatetime().before(new Date())) {
                    activityBO.auto(activity);
                }
            }
        }
        logger.info("***************开始扫描待活动，过期取消***************");
    }
}
