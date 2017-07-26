package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.common.PropertiesUtil;
import com.std.gym.dao.ICoachDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.Coach;

@Repository("coachDAOImpl")
public class CoachDAOImpl extends AMybatisTemplate implements ICoachDAO {

    @Override
    public int insert(Coach data) {
        return super.insert(NAMESPACE.concat("insert_coach"), data);
    }

    @Override
    public int delete(Coach data) {
        return super.delete(NAMESPACE.concat("delete_coach"), data);
    }

    @Override
    public Coach select(Coach condition) {
        condition.setUserDB(PropertiesUtil.Config.USER_DB);
        return super.select(NAMESPACE.concat("select_coach"), condition,
            Coach.class);
    }

    @Override
    public Long selectTotalCount(Coach condition) {
        condition.setUserDB(PropertiesUtil.Config.USER_DB);
        return super.selectTotalCount(NAMESPACE.concat("select_coach_count"),
            condition);
    }

    @Override
    public List<Coach> selectList(Coach condition) {
        condition.setUserDB(PropertiesUtil.Config.USER_DB);
        return super.selectList(NAMESPACE.concat("select_coach"), condition,
            Coach.class);
    }

    @Override
    public List<Coach> selectList(Coach condition, int start, int count) {
        condition.setUserDB(PropertiesUtil.Config.USER_DB);
        return super.selectList(NAMESPACE.concat("select_coach"), start, count,
            condition, Coach.class);
    }

    @Override
    public int update(Coach data) {
        return super.update(NAMESPACE.concat("update_coach"), data);
    }

    @Override
    public int approveCoach(Coach data) {
        return super.update(NAMESPACE.concat("update_approveCoach"), data);
    }

    @Override
    public Long selectFrontTotalCount(Coach condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_coach_sx_count"), condition);
    }

    @Override
    public List<Coach> queryFrontCoachList(Coach condition) {
        return super.selectList(NAMESPACE.concat("select_coach_sx"), condition,
            Coach.class);
    }

    @Override
    public List<Coach> queryFrontCoachList(Coach condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_coach_sx"), start,
            count, condition, Coach.class);
    }

    @Override
    public int updateStar(Coach data) {
        return super.update(NAMESPACE.concat("update_star"), data);
    }
}
