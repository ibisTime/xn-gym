package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IOrgCourseBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IOrgCourseDAO;
import com.std.gym.domain.OrgCourse;
import com.std.gym.enums.EPrefixCode;
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
    public String saveOrgCourse(OrgCourse data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.ORGCOURSE.getCode());
            data.setCode(code);
            orgCourseDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeOrgCourse(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            OrgCourse data = new OrgCourse();
            data.setCode(code);
            count = orgCourseDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshOrgCourse(OrgCourse data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = orgCourseDAO.update(data);
        }
        return count;
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
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
