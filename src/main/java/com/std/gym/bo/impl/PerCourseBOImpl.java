package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IPerCourseDAO;
import com.std.gym.domain.PerCourse;
import com.std.gym.enums.EPrefixCode;
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
    public String savePerCourse(PerCourse data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.PERCOURSE.getCode());
            data.setCode(code);
            perCourseDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removePerCourse(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            PerCourse data = new PerCourse();
            data.setCode(code);
            count = perCourseDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPerCourse(PerCourse data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = perCourseDAO.update(data);
        }
        return count;
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
