package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IOrgCourseDAO;
import com.std.gym.domain.OrgCourse;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.exception.BizException;

@Component
public class OrgCourseBOImpl extends PaginableBOImpl<OrgCourse> implements
        IOrgCourseBO {

    @Autowired
    private IOrgCourseDAO orgCourseDAO;

    @Override
    public boolean isOrgCourseExist(String code) {
        OrgCourse condition = new OrgCourse();
        condition.setCode(code);
        if (orgCourseDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveOrgCourse(OrgCourse data) {
        orgCourseDAO.insert(data);
    }

    @Override
    public void removeOrgCourse(String code) {
        if (StringUtils.isNotBlank(code)) {
            OrgCourse data = new OrgCourse();
            data.setCode(code);
            orgCourseDAO.delete(data);
        }
    }

    @Override
    public void refreshOrgCourse(OrgCourse data) {
        orgCourseDAO.update(data);
    }

    @Override
    public void putOn(OrgCourse orgCourse, String location, Integer orderNo,
            String updater, String remark) {
        orgCourse.setStatus(EActivityStatus.ONLINE.getCode());
        orgCourse.setLocation(location);
        orgCourse.setOrderNo(orderNo);
        orgCourse.setUpdater(updater);
        orgCourse.setUpdateDatetime(new Date());
        orgCourse.setRemark(remark);
        orgCourseDAO.putOn(orgCourse);
    }

    @Override
    public void putOff(OrgCourse orgCourse, String updater, String remark) {
        orgCourse.setStatus(EActivityStatus.OFFLINE.getCode());
        orgCourse.setUpdater(updater);
        orgCourse.setUpdateDatetime(new Date());
        orgCourse.setRemark(remark);
        orgCourseDAO.putOff(orgCourse);
    }

    @Override
    public void stopSign(OrgCourse orgCourse, String updater, String remark) {
        orgCourse.setStatus(EActivityStatus.END.getCode());
        orgCourse.setUpdater(updater);
        orgCourse.setUpdateDatetime(new Date());
        orgCourse.setRemark(remark);
        orgCourseDAO.stopSign(orgCourse);
    }

    @Override
    public List<OrgCourse> queryOrgCourseList(OrgCourse condition) {
        return orgCourseDAO.selectList(condition);
    }

    @Override
    public OrgCourse getOrgCourse(String code) {
        OrgCourse data = null;
        if (StringUtils.isNotBlank(code)) {
            OrgCourse condition = new OrgCourse();
            condition.setCode(code);
            data = orgCourseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "团课不存在");
            }
        }
        return data;
    }

    @Override
    public void addSignNum(OrgCourse orgCourse, Integer quantity) {
        orgCourse.setRemainNum(orgCourse.getRemainNum() - quantity);
        orgCourseDAO.addSignNum(orgCourse);
    }

}
