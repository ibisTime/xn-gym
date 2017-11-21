package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.gym.ao.ICoachAO;
import com.std.gym.bo.IActivityBO;
import com.std.gym.bo.IAttendBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.ISmsOutBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Page;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Activity;
import com.std.gym.domain.Attend;
import com.std.gym.domain.Coach;
import com.std.gym.domain.Comment;
import com.std.gym.domain.PerCourse;
import com.std.gym.domain.User;
import com.std.gym.dto.req.XN622090Req;
import com.std.gym.dto.req.XN622091Req;
import com.std.gym.dto.req.XN622221Req;
import com.std.gym.dto.req.XN622251Req;
import com.std.gym.dto.res.XN622094Res;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ECoachStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.ESystemCode;
import com.std.gym.enums.EUserKind;
import com.std.gym.exception.BizException;

@Service
public class CoachAOImpl implements ICoachAO {

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IActivityBO activityBO;

    @Autowired
    private IAttendBO attendBO;

    @Autowired
    private IPerCourseBO perCourseBO;

    @Autowired
    private IPerCourseOrderBO perCourseOrderBO;

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    public String addCoach(XN622090Req req) {
        User user = userBO.getRemoteUser(req.getUserId());
        String type = EBoolean.YES.getCode();
        if (user.getKind() != null
                && user.getKind().equals(EUserKind.F2.getCode())) {
            StringValidater.validateBlank(req.getIdPhoto());
            type = EBoolean.NO.getCode();
        }
        Coach condition = new Coach();
        condition.setUserId(req.getUserId());
        Long num = coachBO.getTotalCount(condition);
        if (num > 0) {
            throw new BizException("xn0000", "您已添加过信息,可直接修改");
        }
        Coach data = new Coach();
        String code = OrderNoGenerater.generate(EPrefixCode.COACH.getCode());
        data.setCode(code);
        data.setType(type);
        data.setUserId(req.getUserId());
        data.setRealName(req.getRealName());
        data.setPic(req.getPic());
        data.setIdPhoto(req.getIdPhoto());
        data.setPdf(req.getPdf());
        data.setAdvPic(req.getAdvPic());
        data.setGender(req.getGender());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setStar(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setStarNum(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setTeachNum(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setLocation(EBoolean.NO.getCode());
        data.setCreditAmount(0L);
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
        if (ECoachStatus.APPROVE_NO.getCode().equals(data.getStatus())
                || ECoachStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            data.setStatus(ECoachStatus.TO_APPROVE.getCode());
            data.setRealName(req.getRealName());
            data.setGender(req.getGender());
            data.setPdf(req.getPdf());
            data.setIdPhoto(req.getIdPhoto());
        }
        if (null != req.getRealName()
                && !data.getRealName().equals(req.getRealName())) {
            throw new BizException("xn0000", "姓名不可修改");
        }
        if (null != req.getGender()
                && !data.getGender().equals(req.getGender())) {
            throw new BizException("xn0000", "性别不可修改");
        }
        if (null != req.getPdf() && !data.getPdf().equals(req.getPdf())) {
            throw new BizException("xn0000", "证件不可修改");
        }
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setLabel(req.getLabel());
        data.setDescription(req.getDescription());
        coachBO.refreshCoach(data);
    }

    @Override
    public void approveCoach(String code, String result, String creditAmount,
            String approver, String remark) {
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
        if (ECoachStatus.APPROVE_YES.getCode().equals(status.getCode())) {
            StringValidater.validateBlank(creditAmount);
        }
        String type = null;
        if (EBoolean.NO.getCode().equals(data.getType())) {
            type = "教练";
        } else if (EBoolean.YES.getCode().equals(data.getType())) {
            type = "达人";
        }
        smsOutBO.sentContent(data.getUserId(), "尊敬的用户,您提交的" + type + "申请"
                + status.getValue() + ",详情请登录网站进行查看。");
        coachBO.approveCoach(data, status.getCode(), creditAmount, approver,
            remark);
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
        if (StringUtils.isNotBlank(condition.getClassDatetime())) {
            condition.setClassDatetimeStart(DateUtil.getStartDatetime(condition
                .getClassDatetime()));
            condition.setClassDatetimeEnd(DateUtil.getEndDatetime(condition
                .getClassDatetime()));
        }
        condition.setpStatus(EBoolean.NO.getCode());
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
        this.fullPerCourse(perCourseList);
        res.setCoach(coach);
        res.setPerCourseList(perCourseList);
        res.setCommentList(commentList);
        return res;
    }

    private void fullPerCourse(List<PerCourse> list) {
        for (PerCourse perCourse : list) {
            perCourse.setIsAppoint(EBoolean.NO.getCode());
            Long skCount = perCourseOrderBO.getTotalCount(perCourse.getCode(),
                perCourse.getSkStartDatetime(), perCourse.getSkEndDatetime());
            if (skCount > 0) {
                perCourse.setIsAppoint(EBoolean.YES.getCode());
            }
        }
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String remark) {
        Coach coach = coachBO.getCoach(code);
        Map<String, ECoachStatus> status = ECoachStatus.getDictTypeMap();
        if (ECoachStatus.APPROVE_NO.getCode().equals(coach.getStatus())
                || ECoachStatus.TO_APPROVE.getCode().equals(coach.getStatus())
                || ECoachStatus.PUTON.getCode().equals(coach.getStatus())) {
            ECoachStatus statusValue = status.get(coach.getStatus());
            throw new BizException("xn0000", "该私教" + statusValue.getValue()
                    + ",不能上架");
        }
        coachBO.refreshCoach(coach, location, orderNo, remark);
    }

    @Override
    @Transactional
    public String registerCoach(XN622221Req req) {
        String userId = userBO.doAddUser(req.getMobile(), req.getRealName(),
            req.getUserReferee(), "updater", req.getKind(), "0",
            ESystemCode.SYSTEM_CODE.getCode());
        String type = EBoolean.NO.getCode();
        if (EUserKind.F3.getCode().equals(req.getKind())) {
            type = EBoolean.YES.getCode();
        }
        Coach data = new Coach();
        String code = OrderNoGenerater.generate(EPrefixCode.COACH.getCode());
        data.setCode(code);
        data.setType(type);
        data.setUserId(userId);
        data.setRealName(req.getRealName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setGender(req.getGender());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setStar(0);
        data.setStarNum(0);
        data.setLocation(EBoolean.NO.getCode());
        data.setPdf(req.getPdf());
        data.setOrderNo(0);
        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setStatus(ECoachStatus.TO_APPROVE.getCode());
        data.setLabel(req.getLabel());
        data.setDescription(req.getDescription());
        data.setTeachNum(0);
        data.setSumCom(0);
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        coachBO.saveCoach(data);
        Activity activity = activityBO.getActivity(req.getActivityCode());
        if (activity.getEndDatetime().before(new Date())
                || !EActivityStatus.ONLINE.getCode().equals(
                    activity.getStatus())) {
            throw new BizException("xn0000", "活动已经结束");
        }
        List<Attend> attendList = attendBO.queryAttendList(userId,
            req.getActivityCode());
        if (CollectionUtils.isNotEmpty(attendList)) {
            throw new BizException("xn0000", "您已经报名参加了");
        }
        Long count = attendBO.getTotalCount(type, req.getActivityCode());
        Integer number = count.intValue();
        attendBO.saveAttend(data, activity, number);
        return code;
    }

    @Override
    public void putOff(String code) {
        Coach coach = coachBO.getCoach(code);
        if (!ECoachStatus.PUTON.getCode().equals(coach.getStatus())) {
            throw new BizException("xn0000", "该私教还未上架,不能下架");
        }
        coachBO.putOff(coach);
    }

    @Override
    public void editCoachOss(XN622251Req req) {
        Coach data = coachBO.getCoach(req.getCode());
        data.setType(req.getType());
        data.setRealName(req.getRealName());
        data.setGender(req.getGender());
        data.setPdf(req.getPdf());
        data.setIdPhoto(req.getIdPhoto());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setAge(StringValidater.toInteger(req.getAge()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setCreditAmount(StringValidater.toLong(req.getCreditAmount()));
        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setLabel(req.getLabel());
        data.setDescription(req.getDescription());
        coachBO.refreshCoach(data);
    }
}
