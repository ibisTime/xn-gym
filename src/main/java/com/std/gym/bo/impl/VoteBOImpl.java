package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IVoteBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.common.DateUtil;
import com.std.gym.dao.IVoteDAO;
import com.std.gym.domain.Vote;
import com.std.gym.exception.BizException;

@Component
public class VoteBOImpl extends PaginableBOImpl<Vote> implements IVoteBO {

    @Autowired
    private IVoteDAO voteDAO;

    @Override
    public boolean isVoteExist(String code) {
        Vote condition = new Vote();
        condition.setCode(code);
        if (voteDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveVote(Vote data) {
        voteDAO.insert(data);
    }

    @Override
    public int removeVote(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Vote data = new Vote();
            data.setCode(code);
            count = voteDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshVote(Vote data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = voteDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Vote> queryVoteList(Vote condition) {
        return voteDAO.selectList(condition);
    }

    @Override
    public Vote getVote(String code) {
        Vote data = null;
        if (StringUtils.isNotBlank(code)) {
            Vote condition = new Vote();
            condition.setCode(code);
            data = voteDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public Long getTotalCount(String type, String userId, String toUser) {
        Date createVoteDatetime = DateUtil.getAnyOneStart(new Date());
        Date endVoteDatetime = DateUtil.getAnyOneEnd(new Date());
        Vote condition = new Vote();
        condition.setVoteType(type);
        condition.setToUser(toUser);
        condition.setUserId(userId);
        condition.setCreateVoteDatetime(createVoteDatetime);
        condition.setEndVoteDatetime(endVoteDatetime);
        return voteDAO.selectTotalCount(condition);
    }
}
