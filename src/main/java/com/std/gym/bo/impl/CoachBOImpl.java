package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.StringValidater;
import com.std.gym.dao.ICoachDAO;
import com.std.gym.domain.Coach;
import com.std.gym.enums.ECoachStatus;
import com.std.gym.exception.BizException;

@Component
public class CoachBOImpl extends PaginableBOImpl<Coach> implements ICoachBO {

    @Autowired
    private ICoachDAO coachDAO;

    @Override
    public boolean isCoachExist(String code) {
        Coach condition = new Coach();
        condition.setCode(code);
        if (coachDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveCoach(Coach data) {
        coachDAO.insert(data);
    }

    @Override
    public void removeCoach(String code) {
        if (StringUtils.isNotBlank(code)) {
            Coach data = new Coach();
            data.setCode(code);
            coachDAO.delete(data);
        }
    }

    @Override
    public void refreshCoach(Coach data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            coachDAO.update(data);
        }
    }

    @Override
    public void approveCoach(Coach data, ECoachStatus status, String approver,
            String remark) {
        data.setStatus(status.getCode());
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        coachDAO.approveCoach(data);
    }

    @Override
    public List<Coach> queryCoachList(Coach condition) {
        return coachDAO.selectList(condition);
    }

    @Override
    public Coach getCoach(String code) {
        Coach data = null;
        if (StringUtils.isNotBlank(code)) {
            Coach condition = new Coach();
            condition.setCode(code);
            data = coachDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "私教编号不存在");
            }
        }
        return data;
    }

    @Override
    public Coach getCoachByUserId(String userId) {
        Coach data = null;
        if (StringUtils.isNotBlank(userId)) {
            Coach condition = new Coach();
            condition.setUserId(userId);
            data = coachDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "私教编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Coach> queryFrontCoachList(Coach condition) {
        return coachDAO.queryFrontCoachList(condition);
    }

    @Override
    public List<Coach> queryFrontCoachList(Coach condition, int start, int limit) {
        return coachDAO.queryFrontCoachList(condition, start, limit);
    }

    @Override
    public void updateStar(Coach coach, Integer star, Integer starNum) {
        coach.setStar(star);
        coach.setStarNum(starNum);
        coach.setSumCom(coach.getSumCom() + 1);
        coachDAO.updateStar(coach);
    }

    @Override
    public void refreshCoach(Coach coach, String location, String orderNo) {
        coach.setLocation(location);
        coach.setOrderNo(StringValidater.toInteger(orderNo));
        coachDAO.updateLocation(coach);
    }
}
