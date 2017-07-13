package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IOrgCourseOrderDAO;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Component
public class OrgCourseOrderBOImpl extends PaginableBOImpl<OrgCourseOrder>
        implements IOrgCourseOrderBO {

    @Autowired
    private IOrgCourseOrderDAO orgCourseOrderDAO;

    @Override
    public boolean isOrgCourseOrderExist(String code) {
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setCode(code);
        if (orgCourseOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveOrgCourseOrder(OrgCourseOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.ORGCOURSEORDER
                .getCode());
            data.setCode(code);
            orgCourseOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeOrgCourseOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            OrgCourseOrder data = new OrgCourseOrder();
            data.setCode(code);
            count = orgCourseOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshOrgCourseOrder(OrgCourseOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = orgCourseOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition) {
        return orgCourseOrderDAO.selectList(condition);
    }

    @Override
    public OrgCourseOrder getOrgCourseOrder(String code) {
        OrgCourseOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            OrgCourseOrder condition = new OrgCourseOrder();
            condition.setCode(code);
            data = orgCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
