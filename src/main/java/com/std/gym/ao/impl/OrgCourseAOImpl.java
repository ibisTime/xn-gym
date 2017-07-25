package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IOrgCourseAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.common.DateUtil;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.domain.OrgCourse;
import com.std.gym.dto.req.XN622050Req;
import com.std.gym.dto.req.XN622052Req;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Service
public class OrgCourseAOImpl implements IOrgCourseAO {

    @Autowired
    private IOrgCourseBO orgCourseBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICoachBO coachBO;

    @Override
    public String addOrgCourse(XN622050Req req) {
        userBO.getRemoteUser(req.getCoachUser());
        OrgCourse data = new OrgCourse();
        String code = OrderNoGenerater
            .generate(EPrefixCode.ORGCOURSE.getCode());
        data.setCode(code);
        data.setCoachUser(req.getCoachUser());
        data.setName(req.getName());
        data.setSkStartDatetime(DateUtil.strToDate(req.getSkStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        data.setSkEndDatetime(DateUtil.strToDate(req.getSkEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        data.setTotalNum(StringValidater.toInteger(req.getTotalNum()));
        data.setRemainNum(StringValidater.toInteger(req.getTotalNum()));
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setContact(req.getContact());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setDescription(req.getDescription());
        data.setSumCom(StringValidater.toInteger(EBoolean.NO.getCode()));
        data.setStatus(EActivityStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        orgCourseBO.saveOrgCourse(data);
        return code;
    }

    @Override
    public void editOrgCourse(XN622052Req req) {
        OrgCourse data = orgCourseBO.getOrgCourse(req.getCode());
        if (EActivityStatus.DRAFT.getCode().equals(data.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(data.getStatus())) {
            Integer totalNum = StringValidater.toInteger(req.getTotalNum());
            Integer number = data.getTotalNum() - data.getRemainNum();
            if (totalNum < number) {
                throw new BizException("xn0000", "当前报名人数以超过修改总人数");
            }
            Integer remainNum = totalNum - number;
            data.setCoachUser(req.getCoachUser());
            data.setName(req.getName());
            data.setSkStartDatetime(DateUtil.strToDate(
                req.getSkStartDatetime(), DateUtil.DATA_TIME_PATTERN_1));
            data.setSkEndDatetime(DateUtil.strToDate(req.getSkEndDatetime(),
                DateUtil.DATA_TIME_PATTERN_1));
            data.setTotalNum(StringValidater.toInteger(req.getTotalNum()));
            data.setRemainNum(remainNum);
            data.setProvince(req.getProvince());
            data.setCity(req.getCity());
            data.setArea(req.getArea());
            data.setAddress(req.getAddress());
            data.setContact(req.getContact());
            data.setPic(req.getPic());
            data.setAdvPic(req.getAdvPic());
            data.setPrice(StringValidater.toLong(req.getPrice()));
            data.setDescription(req.getDescription());
            data.setUpdater(req.getUpdater());
            data.setUpdateDatetime(new Date());
            data.setRemark(req.getRemark());
            orgCourseBO.refreshOrgCourse(data);
            return;
        }
        throw new BizException("xn0000", "该状态下不能修改");
    }

    @Override
    public void dropOrgCourse(String code) {
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(code);
        if (!EActivityStatus.DRAFT.getCode().equals(orgCourse.getStatus())) {
            throw new BizException("xn0000", "该状态下不能删除");
        }
        orgCourseBO.removeOrgCourse(code);
    }

    @Override
    public void putOn(String code, String location, Integer orderNo,
            String updater, String remark) {
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(code);
        if (EActivityStatus.ONLINE.getCode().equals(orgCourse.getStatus())) {
            throw new BizException("xn0000", "该团课已上架");
        }
        orgCourseBO.putOn(orgCourse, location, orderNo, updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(code);
        if (!EActivityStatus.ONLINE.getCode().equals(orgCourse.getStatus())) {
            throw new BizException("xn0000", "该团课没有上架,不能下架");
        }
        orgCourseBO.putOff(orgCourse, updater, remark);
    }

    @Override
    public void stopSign(String code, String updater, String remark) {
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(code);
        if (!EActivityStatus.ONLINE.getCode().equals(orgCourse.getStatus())) {
            throw new BizException("xn0000", "该团课没有上架,不能截止报名");
        }
        orgCourseBO.stopSign(orgCourse, updater, remark);
    }

    @Override
    public Paginable<OrgCourse> queryOrgCoursePage(int start, int limit,
            OrgCourse condition) {
        if (condition.getClassDatetime() != null) {
            condition.setBeginClassDatetime(DateUtil.getAnyOneStart(condition
                .getClassDatetime()));
            condition.setEndClassDatetime(DateUtil.getAnyOneEnd(condition
                .getClassDatetime()));
            condition.setClassDatetime(null);
        }
        Paginable<OrgCourse> page = orgCourseBO.getPaginable(start, limit,
            condition);
        List<OrgCourse> list = page.getList();
        for (OrgCourse orgCourse : list) {
            Coach coach = coachBO.getCoachByUserId(orgCourse.getCoachUser());
            orgCourse.setRealName(coach.getRealName());
        }
        return page;
    }

    @Override
    public List<OrgCourse> queryOrgCourseList(OrgCourse condition) {
        List<OrgCourse> list = orgCourseBO.queryOrgCourseList(condition);
        for (OrgCourse orgCourse : list) {
            Coach coach = coachBO.getCoachByUserId(orgCourse.getCoachUser());
            orgCourse.setRealName(coach.getRealName());
        }
        return list;
    }

    @Override
    public OrgCourse getOrgCourse(String code) {
        OrgCourse orgCourse = orgCourseBO.getOrgCourse(code);
        Coach coach = coachBO.getCoachByUserId(orgCourse.getCoachUser());
        orgCourse.setRealName(coach.getRealName());
        return orgCourse;
    }
}
