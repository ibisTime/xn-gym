package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IOrgCourseOrderDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.OrgCourseOrder;

@Repository("orgCourseOrderDAOImpl")
public class OrgCourseOrderDAOImpl extends AMybatisTemplate implements
        IOrgCourseOrderDAO {

    @Override
    public int insert(OrgCourseOrder data) {
        return super.insert(NAMESPACE.concat("insert_orgCourseOrder"), data);
    }

    @Override
    public int delete(OrgCourseOrder data) {
        return super.delete(NAMESPACE.concat("delete_orgCourseOrder"), data);
    }

    @Override
    public OrgCourseOrder select(OrgCourseOrder condition) {
        return super.select(NAMESPACE.concat("select_orgCourseOrder"),
            condition, OrgCourseOrder.class);
    }

    @Override
    public Long selectTotalCount(OrgCourseOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_orgCourseOrder_count"), condition);
    }

    @Override
    public List<OrgCourseOrder> selectList(OrgCourseOrder condition) {
        return super.selectList(NAMESPACE.concat("select_orgCourseOrder"),
            condition, OrgCourseOrder.class);
    }

    @Override
    public List<OrgCourseOrder> selectList(OrgCourseOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_orgCourseOrder"),
            start, count, condition, OrgCourseOrder.class);
    }

    @Override
    public int update(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_ordCourseOrder"), data);
    }

    @Override
    public int payGroup(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int paySuccess(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_paySuccess"), data);
    }

    @Override
    public int userCancel(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_userCancel"), data);
    }

    @Override
    public int platCancel(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_platCancel"), data);
    }

    @Override
    public int applyRefund(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_applyRefund"), data);
    }

    @Override
    public int approveRefund(OrgCourseOrder data) {
        return super.update(NAMESPACE.concat("update_approveRefund"), data);
    }

}
