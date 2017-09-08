package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Vote;

public interface IVoteBO extends IPaginableBO<Vote> {

    public boolean isVoteExist(String code);

    public void saveVote(Vote data);

    public int removeVote(String code);

    public int refreshVote(Vote data);

    public List<Vote> queryVoteList(Vote condition);

    public Vote getVote(String code);

    public Long getTotalCount(String type, String userId, String toUser);

}
