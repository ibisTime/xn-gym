package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IAttendBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IAttendDAO;
import com.std.gym.domain.Activity;
import com.std.gym.domain.Attend;
import com.std.gym.domain.Coach;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Component
public class AttendBOImpl extends PaginableBOImpl<Attend> implements IAttendBO {

    @Autowired
    private IAttendDAO attendDAO;

    @Override
    public String saveAttend(Coach coach, Activity activity, Integer number) {
        Attend data = new Attend();
        String code = OrderNoGenerater.generate(EPrefixCode.ATTEND.getCode());
        data.setCode(code);
        data.setType(coach.getType());
        data.setUserId(coach.getUserId());
        data.setCoachCode(coach.getCode());
        data.setActivityCode(activity.getCode());
        data.setJionDatetime(new Date());
        data.setOrderNo(number + 1);
        data.setStartDatetime(activity.getStartDatetime());
        data.setEndDatetime(activity.getEndDatetime());
        data.setTotalNum(0);
        attendDAO.insert(data);
        return code;
    }

    @Override
    public void refreshAttend(Attend data) {
        attendDAO.update(data);
    }

    @Override
    public List<Attend> queryAttendList(Attend condition) {
        return attendDAO.selectList(condition);
    }

    @Override
    public Attend getAttend(String code) {
        Attend data = null;
        if (StringUtils.isNotBlank(code)) {
            Attend condition = new Attend();
            condition.setCode(code);
            data = attendDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Attend> queryAttendList(String userId, String activityCode) {
        Attend condition = new Attend();
        condition.setUserId(userId);
        condition.setActivityCode(activityCode);
        return attendDAO.selectList(condition);
    }

    @Override
    public Long getTotalCount(String type, String activityCode) {
        Attend condition = new Attend();
        condition.setType(type);
        condition.setActivityCode(activityCode);
        return attendDAO.selectTotalCount(condition);
    }

    @Override
    public void refreshAttend(String activityCode, Date startDatetime,
            Date endDatetime) {
        Attend data = new Attend();
        data.setActivityCode(activityCode);
        data.setStartDatetime(startDatetime);
        data.setEndDatetime(endDatetime);
        attendDAO.updateDatetime(data);
    }
}
