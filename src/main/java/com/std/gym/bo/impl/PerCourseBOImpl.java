package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IPerCourseDAO;
import com.std.gym.domain.PerCourse;
import com.std.gym.exception.BizException;

@Component
public class PerCourseBOImpl extends PaginableBOImpl<PerCourse> implements
        IPerCourseBO {

    @Autowired
    private IPerCourseDAO perCourseDAO;

    @Override
    public boolean isPerCourseExist(String code) {
        PerCourse condition = new PerCourse();
        condition.setCode(code);
        if (perCourseDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void savePerCourse(PerCourse data) {
        perCourseDAO.insert(data);
    }

    @Override
    public void removePerCourse(PerCourse perCourse) {
        perCourseDAO.delete(perCourse);
    }

    @Override
    public void refreshPerCourse(PerCourse data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            perCourseDAO.update(data);
        }
    }

    @Override
    public List<PerCourse> queryPerCourseList(PerCourse condition) {
        return perCourseDAO.selectList(condition);
    }

    @Override
    public PerCourse getPerCourse(String code) {
        PerCourse data = null;
        if (StringUtils.isNotBlank(code)) {
            PerCourse condition = new PerCourse();
            condition.setCode(code);
            data = perCourseDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
