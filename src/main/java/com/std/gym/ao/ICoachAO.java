package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Coach;
import com.std.gym.dto.req.XN622090Req;
import com.std.gym.dto.req.XN622091Req;

@Component
public interface ICoachAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCoach(XN622090Req req);

    public void dropCoach(String code);

    public void editCoach(XN622091Req req);

    public void approveCoach(String code, String result, String approver,
            String remark);

    public Paginable<Coach> queryCoachPage(int start, int limit, Coach condition);

    public List<Coach> queryCoachList(Coach condition);

    public Coach getCoach(String code);

    public Coach getCoachByUserId(String userId);

}
