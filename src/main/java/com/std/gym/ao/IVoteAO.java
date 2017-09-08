package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Vote;
import com.std.gym.dto.req.XN622240Req;

@Component
public interface IVoteAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addVote(XN622240Req req);

    public int dropVote(String code);

    public int editVote(Vote data);

    public Paginable<Vote> queryVotePage(int start, int limit, Vote condition);

    public List<Vote> queryVoteList(Vote condition);

    public Vote getVote(String code);

}
