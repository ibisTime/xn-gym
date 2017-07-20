package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IOrgCourseDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.OrgCourse;

@Repository("orgCourseDAOImpl")
public class OrgCourseDAOImpl extends AMybatisTemplate implements IOrgCourseDAO {

    @Override
    public int insert(OrgCourse data) {
        return super.insert(NAMESPACE.concat("insert_orgCourse"), data);
    }

    @Override
    public int delete(OrgCourse data) {
        return super.delete(NAMESPACE.concat("delete_orgCourse"), data);
    }

    @Override
    public OrgCourse select(OrgCourse condition) {
        return super.select(NAMESPACE.concat("select_orgCourse"), condition,
            OrgCourse.class);
    }

    @Override
    public Long selectTotalCount(OrgCourse condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_orgCourse_count"), condition);
    }

    @Override
    public List<OrgCourse> selectList(OrgCourse condition) {
        return super.selectList(NAMESPACE.concat("select_orgCourse"),
            condition, OrgCourse.class);
    }

    @Override
    public List<OrgCourse> selectList(OrgCourse condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_orgCourse"), start,
            count, condition, OrgCourse.class);
    }

    @Override
    public int update(OrgCourse data) {
        return super.update(NAMESPACE.concat("update_orgCourse"), data);
    }

    @Override
    public int putOn(OrgCourse data) {
        return super.update(NAMESPACE.concat("update_putOn"), data);
    }

    @Override
    public int putOff(OrgCourse data) {
        return super.update(NAMESPACE.concat("update_putOff"), data);
    }

    @Override
    public int stopSign(OrgCourse data) {
        return super.update(NAMESPACE.concat("update_stopSign"), data);
    }

    @Override
    public int addSignNum(OrgCourse data) {
        return super.update(NAMESPACE.concat("update_addSignNum"), data);
    }

    @Override
    public int addSumCom(OrgCourse data) {
        return super.update(NAMESPACE.concat("update_addSumCom"), data);
    }
}
