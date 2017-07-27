package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ICoachAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Page;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.domain.Comment;
import com.std.gym.domain.PerCourse;
import com.std.gym.dto.req.XN622090Req;
import com.std.gym.dto.req.XN622091Req;
import com.std.gym.dto.res.XN622094Res;
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

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private ICommentBO commentBO;

    @Override
    public String addCoach(XN622090Req req) {
        userBO.getRemoteUser(req.getUserId());
        Coach condition = new Coach();
        condition.setUserId(req.getUserId());
        Long num = coachBO.getTotalCount(condition);
        if (num > 0) {
            throw new BizException("xn0000", "您已添加过信息,可直接修改");
        }
        Coach data = new Coach();
        String code = OrderNoGenerater.generate(EPrefixCode.COACH.getCode());
        data.setCode(code);
        data.setUserId(req.getUserId());
        data.setRealName(req.getRealName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setGender(req.getGender());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setStar(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setStarNum(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setLocation(EBoolean.NO.getCode());
        data.setOrderNo(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setStatus(ECoachStatus.TO_APPROVE.getCode());
        data.setLabel(req.getLabel());
        data.setDescription(req.getDescription());
        data.setSumCom(StringValidater.toInteger(EBoolean.NO.getCode()));
        coachBO.saveCoach(data);
        return code;
    }

    @Override
    public void editCoach(XN622091Req req) {
        Coach data = coachBO.getCoach(req.getCode());
        if (!ECoachStatus.APPROVE_YES.getCode().equals(data.getStatus())) {
            data.setStatus(ECoachStatus.TO_APPROVE.getCode());
            data.setRealName(req.getRealName());
            data.setGender(req.getGender());
        }
        if (!data.getRealName().equals(req.getRealName())) {
            throw new BizException("xn0000", "姓名不可修改");
        }
        if (!data.getGender().equals(req.getGender())) {
            throw new BizException("xn0000", "性别不可修改");
        }
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setDuration(StringValidater.toInteger(req.getDuration()));
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
    public Paginable<Coach> queryFrontCoachPage(int start, int limit,
            Coach condition) {
        Paginable<Coach> page = null;
        List<Coach> list = coachBO.queryFrontCoachList(condition);
        page = new Page<Coach>(start, limit, list.size());
        List<Coach> dataList = coachBO.queryFrontCoachList(condition,
            page.getStart(), page.getPageSize());
        page.setList(dataList);
        return page;
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

    @Override
    public XN622094Res getFrontCoach(String code) {
        XN622094Res res = new XN622094Res();
        Coach coach = coachBO.getCoach(code);
        List<PerCourse> perCourseList = perCourseBO.queryPerCourseList(code);
        List<Comment> commentList = commentBO.queryCommentList(code, null);
        res.setCoach(coach);
        res.setPerCourseList(perCourseList);
        res.setCommentList(commentList);
        return res;
    }

    @Override
    public void editLocation(String code, String location, String orderNo) {
        Coach coach = coachBO.getCoach(code);
        if (!ECoachStatus.APPROVE_YES.getCode().equals(coach.getStatus())) {
            throw new BizException("xn0000", "该私教还未通过审核,不能设置位置");
        }
        coachBO.refreshCoach(coach, location, orderNo);
    }
}
