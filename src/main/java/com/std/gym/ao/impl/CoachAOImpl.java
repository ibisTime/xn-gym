package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ICoachAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.dto.req.XN622090Req;
import com.std.gym.dto.req.XN622091Req;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECoachStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Service
public class CoachAOImpl implements ICoachAO {

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addCoach(XN622090Req req) {
        userBO.getRemoteUser(req.getUserId());
        Coach condition = new Coach();
        condition.setUserId(req.getUserId());
        Long num = coachBO.getTotalCount(condition);
        if (num != null) {
            throw new BizException("xn0000", "您已添加过信息,可直接修改");
        }
        Coach data = new Coach();
        String code = OrderNoGenerater.generate(EPrefixCode.COACH.getCode());
        data.setCode(code);
        data.setUserId(req.getUserId());
        data.setRealName(req.getRealName());
        data.setPic(req.getPic());
        data.setGender(req.getGender());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setStar(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setStarNum(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setStatus(ECoachStatus.TO_APPROVE.getCode());
        data.setLabel(req.getLabel());
        data.setDescription(req.getDescription());
        coachBO.saveCoach(data);
        return code;
    }

    @Override
    public void editCoach(XN622091Req req) {
        Coach data = coachBO.getCoach(req.getCode());
        if (ECoachStatus.APPROVE_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "审批已通过，不可随意修改");
        }
        data.setRealName(req.getRealName());
        data.setPic(req.getPic());
        data.setGender(req.getGender());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setStatus(ECoachStatus.TO_APPROVE.getCode());
        data.setLabel(req.getLabel());
        data.setDescription(req.getDescription());
        coachBO.refreshCoach(data);
    }

    @Override
    public void approveCoach(String code, String result, String approver,
            String remark) {
        Coach data = coachBO.getCoach(code);
        if (!ECoachStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该信息已审批过,无需再次审批");
        }
        ECoachStatus status = null;
        if (EBoolean.NO.getCode().equals(result)) {
            status = ECoachStatus.APPROVE_NO;
        } else if (EBoolean.YES.getCode().equals(result)) {
            status = ECoachStatus.APPROVE_YES;
        }
        coachBO.approveCoach(data, status, approver, remark);
    }

    @Override
    public void dropCoach(String code) {
        if (!coachBO.isCoachExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        coachBO.removeCoach(code);
    }

    @Override
    public Paginable<Coach> queryCoachPage(int start, int limit, Coach condition) {
        return coachBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Coach> queryCoachList(Coach condition) {
        return coachBO.queryCoachList(condition);
    }

    @Override
    public Coach getCoach(String code) {
        return coachBO.getCoach(code);
    }

    @Override
    public Coach getCoachByUserId(String userId) {
        return coachBO.getCoachByUserId(userId);
    }

}
