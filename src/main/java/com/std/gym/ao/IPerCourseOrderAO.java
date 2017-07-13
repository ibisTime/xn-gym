package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PerCourseOrder;

@Component
public interface IPerCourseOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPerCourseOrder(PerCourseOrder data);

    public int dropPerCourseOrder(String code);

    public int editPerCourseOrder(PerCourseOrder data);

    public Paginable<PerCourseOrder> queryPerCourseOrderPage(int start,
            int limit, PerCourseOrder condition);

    public List<PerCourseOrder> queryPerCourseOrderList(PerCourseOrder condition);

    public PerCourseOrder getPerCourseOrder(String code);

}
