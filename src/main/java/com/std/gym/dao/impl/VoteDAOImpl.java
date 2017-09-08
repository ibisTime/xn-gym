package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.IVoteDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.Vote;

@Repository("voteDAOImpl")
public class VoteDAOImpl extends AMybatisTemplate implements IVoteDAO {

    @Override
    public int insert(Vote data) {
        return super.insert(NAMESPACE.concat("insert_vote"), data);
    }

    @Override
    public int delete(Vote data) {
        return super.delete(NAMESPACE.concat("delete_vote"), data);
    }

    @Override
    public Vote select(Vote condition) {
        return super.select(NAMESPACE.concat("select_vote"), condition,
            Vote.class);
    }

    @Override
    public Long selectTotalCount(Vote condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_vote_count"),
            condition);
    }

    @Override
    public List<Vote> selectList(Vote condition) {
        return super.selectList(NAMESPACE.concat("select_vote"), condition,
            Vote.class);
    }

    @Override
    public List<Vote> selectList(Vote condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_vote"), start, count,
            condition, Vote.class);
    }

    @Override
    public int update(Vote data) {
        // TODO Auto-generated method stub
        return 0;
    }

}
