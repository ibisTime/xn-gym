package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IPerCourseOrderDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.PerCourseOrder;

@Repository("perCourseOrderDAOImpl")
public class PerCourseOrderDAOImpl extends AMybatisTemplate implements
        IPerCourseOrderDAO {

    @Override
    public int insert(PerCourseOrder data) {
        return super.insert(NAMESPACE.concat("insert_perCourseOrder"), data);
    }

    @Override
    public int delete(PerCourseOrder data) {
        return super.delete(NAMESPACE.concat("delete_perCourseOrder"), data);
    }

    @Override
    public PerCourseOrder select(PerCourseOrder condition) {
        return super.select(NAMESPACE.concat("select_perCourseOrder"),
            condition, PerCourseOrder.class);
    }

    @Override
    public Long selectTotalCount(PerCourseOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_perCourseOrder_count"), condition);
    }

    @Override
    public List<PerCourseOrder> selectList(PerCourseOrder condition) {
        return super.selectList(NAMESPACE.concat("select_perCourseOrder"),
            condition, PerCourseOrder.class);
    }

    @Override
    public List<PerCourseOrder> selectList(PerCourseOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_perCourseOrder"),
            start, count, condition, PerCourseOrder.class);
    }

    @Override
    public int update(PerCourseOrder data) {
        return super.update(NAMESPACE.concat("update_perCourseOrder"), data);
    }

    @Override
    public int payGroup(PerCourseOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int paySuccess(PerCourseOrder data) {
        return super.update(NAMESPACE.concat("update_paySuccess"), data);
    }

    @Override
    public int receiverOrder(PerCourseOrder data) {
        return super.update(NAMESPACE.concat("update_receiverOrder"), data);
    }

    @Override
    public int classBegin(PerCourseOrder data) {
        return super.update(NAMESPACE.concat("update_skClassDatetime"), data);
    }

    @Override
    public int classOver(PerCourseOrder data) {
        return super.update(NAMESPACE.concat("update_xkClassDatetime"), data);
    }
}
