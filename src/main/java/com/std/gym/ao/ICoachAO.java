package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Coach;

@Component
public interface ICoachAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCoach(Coach data);

    public int dropCoach(String code);

    public int editCoach(Coach data);

    public Paginable<Coach> queryCoachPage(int start, int limit, Coach condition);

    public List<Coach> queryCoachList(Coach condition);

    public Coach getCoach(String code);

}
