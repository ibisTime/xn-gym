package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.ICoachDAO;
import com.std.gym.domain.Coach;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Component
public class CoachBOImpl extends PaginableBOImpl<Coach> implements ICoachBO {

    @Autowired
    private ICoachDAO coachDAO;

    @Override
    public boolean isCoachExist(String code) {
        Coach condition = new Coach();
        condition.setCode(code);
        if (coachDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCoach(Coach data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.COACH.getCode());
            data.setCode(code);
            coachDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCoach(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Coach data = new Coach();
            data.setCode(code);
            count = coachDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCoach(Coach data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = coachDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Coach> queryCoachList(Coach condition) {
        return coachDAO.selectList(condition);
    }

    @Override
    public Coach getCoach(String code) {
        Coach data = null;
        if (StringUtils.isNotBlank(code)) {
            Coach condition = new Coach();
            condition.setCode(code);
            data = coachDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
