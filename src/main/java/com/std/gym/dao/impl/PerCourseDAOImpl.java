package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IPerCourseDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.PerCourse;

@Repository("perCourseDAOImpl")
public class PerCourseDAOImpl extends AMybatisTemplate implements IPerCourseDAO {

    @Override
    public int insert(PerCourse data) {
        return super.insert(NAMESPACE.concat("insert_perCourse"), data);
    }

    @Override
    public int delete(PerCourse data) {
        return super.delete(NAMESPACE.concat("delete_perCourse"), data);
    }

    @Override
    public PerCourse select(PerCourse condition) {
        return super.select(NAMESPACE.concat("select_perCourse"), condition,
            PerCourse.class);
    }

    @Override
    public Long selectTotalCount(PerCourse condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_perCourse_count"), condition);
    }

    @Override
    public List<PerCourse> selectList(PerCourse condition) {
        return super.selectList(NAMESPACE.concat("select_perCourse"),
            condition, PerCourse.class);
    }

    @Override
    public List<PerCourse> selectList(PerCourse condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_perCourse"), start,
            count, condition, PerCourse.class);
    }

    @Override
    public int update(PerCourse data) {
        return super.update(NAMESPACE.concat("update_perCourse"), data);
    }

    @Override
    public int updateStatus(PerCourse data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
