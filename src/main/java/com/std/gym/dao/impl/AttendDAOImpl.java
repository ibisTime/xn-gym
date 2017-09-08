package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IAttendDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.Attend;

@Repository("attendDAOImpl")
public class AttendDAOImpl extends AMybatisTemplate implements IAttendDAO {

    @Override
    public int insert(Attend data) {
        return super.insert(NAMESPACE.concat("insert_attend"), data);
    }

    @Override
    public int delete(Attend data) {
        return super.delete(NAMESPACE.concat("delete_attend"), data);
    }

    @Override
    public Attend select(Attend condition) {
        return super.select(NAMESPACE.concat("select_attend"), condition,
            Attend.class);
    }

    @Override
    public Long selectTotalCount(Attend condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_attend_count"),
            condition);
    }

    @Override
    public List<Attend> selectList(Attend condition) {
        return super.selectList(NAMESPACE.concat("select_attend"), condition,
            Attend.class);
    }

    @Override
    public List<Attend> selectList(Attend condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_attend"), start,
            count, condition, Attend.class);
    }

    @Override
    public int update(Attend data) {
        return super.update(NAMESPACE.concat("update_attend"), data);
    }

}
