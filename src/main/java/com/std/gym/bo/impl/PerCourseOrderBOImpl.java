package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IPerCourseOrderBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IPerCourseOrderDAO;
import com.std.gym.domain.PerCourseOrder;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Component
public class PerCourseOrderBOImpl extends PaginableBOImpl<PerCourseOrder>
        implements IPerCourseOrderBO {

    @Autowired
    private IPerCourseOrderDAO perCourseOrderDAO;

    @Override
    public boolean isPerCourseOrderExist(String code) {
        PerCourseOrder condition = new PerCourseOrder();
        condition.setCode(code);
        if (perCourseOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String savePerCourseOrder(PerCourseOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.PERCOURSEORDER
                .getCode());
            data.setCode(code);
            perCourseOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removePerCourseOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            PerCourseOrder data = new PerCourseOrder();
            data.setCode(code);
            count = perCourseOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPerCourseOrder(PerCourseOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = perCourseOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition) {
        return perCourseOrderDAO.selectList(condition);
    }

    @Override
    public PerCourseOrder getPerCourseOrder(String code) {
        PerCourseOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            PerCourseOrder condition = new PerCourseOrder();
            condition.setCode(code);
            data = perCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
