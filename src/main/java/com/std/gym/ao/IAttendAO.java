package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Attend;

@Component
public interface IAttendAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addAttend(String loginName, String loginPwd, String kind,
            String activityCode);

    public void editAttend(Attend data);

    public Paginable<Attend> queryAttendPage(int start, int limit,
            Attend condition, String userId);

    public List<Attend> queryAttendList(Attend condition, String userId);

    public Attend getAttend(String code, String userId);

}
