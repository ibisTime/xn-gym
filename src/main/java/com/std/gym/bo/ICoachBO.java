package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Coach;
import com.std.gym.enums.ECoachStatus;

public interface ICoachBO extends IPaginableBO<Coach> {

    public boolean isCoachExist(String code);

    public void saveCoach(Coach data);

    public void removeCoach(String code);

    public void refreshCoach(Coach data);

    public void approveCoach(Coach data, ECoachStatus status, String approver,
            String remark);

    public List<Coach> queryCoachList(Coach condition);

    public Coach getCoach(String code);

    public Coach getCoachByUserId(String userId);

    public List<Coach> queryFrontCoachList(Coach condition);

    public List<Coach> queryFrontCoachList(Coach condition, int start,
            int pageSize);

    public void updateStar(Coach coach, Integer star, Integer starNum);

}
