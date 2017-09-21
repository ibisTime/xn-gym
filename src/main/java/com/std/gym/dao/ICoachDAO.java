package com.std.gym.dao;

import java.util.List;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.Coach;

public interface ICoachDAO extends IBaseDAO<Coach> {
    String NAMESPACE = ICoachDAO.class.getName().concat(".");

    int update(Coach data);

    int approveCoach(Coach data);

    List<Coach> queryFrontCoachList(Coach data);

    Long selectFrontTotalCount(Coach condition);

    List<Coach> queryFrontCoachList(Coach condition, int start, int count);

    int updateStar(Coach coach);

    int updateLocation(Coach coach);

    int putOff(Coach coach);

    int teachNum(Coach data);
}
