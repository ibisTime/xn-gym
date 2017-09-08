package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Activity;
import com.std.gym.domain.Attend;
import com.std.gym.domain.Coach;

public interface IAttendBO extends IPaginableBO<Attend> {

    public String saveAttend(Coach coach, Activity activity);

    public void refreshAttend(Attend data);

    public List<Attend> queryAttendList(Attend condition);

    public Attend getAttend(String code);

    public List<Attend> queryAttendList(String userId, String activityCode);

}
