package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IVoteAO;
import com.std.gym.bo.IActivityBO;
import com.std.gym.bo.IAttendBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.IVoteBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Activity;
import com.std.gym.domain.Attend;
import com.std.gym.domain.Vote;
import com.std.gym.dto.req.XN622240Req;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.ESysConfigType;
import com.std.gym.exception.BizException;

@Service
public class VoteAOImpl implements IVoteAO {

    @Autowired
    private IVoteBO voteBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IAttendBO attendBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IActivityBO activityBO;

    @Override
    public String addVote(XN622240Req req) {
        // 判断是否参加
        Attend attend = attendBO.getAttend(req.getAttendCode());
        Activity activity = activityBO.getActivity(attend.getActivityCode());
        if (!EActivityStatus.ONLINE.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动不能投票");
        }
        if (attend.getStartDatetime().after(new Date())) {
            throw new BizException("xn0000", "投票还未开始");
        }
        if (attend.getEndDatetime().before(new Date())) {
            throw new BizException("xn0000", "投票已经截止");
        }

        Map<String, String> map = sysConfigBO
            .querySYSConfigMap(ESysConfigType.VOTE_RULE.getCode());
        // 判断今天投多少票
        Long toDayNum = voteBO.getTotalCount(null, req.getUserId(), null);
        if (toDayNum >= StringValidater.toInteger(map.get("EVERYVOTENUM"))) {
            throw new BizException("xn0000", "抱歉,您今天的投票数已经超过最大投票数");
        }
        if (EBoolean.NO.getCode().equals(attend.getType())) {
            // 判断达人投了多少票
            Long drNum = voteBO.getTotalCount(EBoolean.NO.getCode(),
                req.getUserId(), null);
            if (drNum >= StringValidater.toInteger(map.get("DRVOTE"))) {
                throw new BizException("xn0000", "抱歉,您今天对达人的投票数已经超过最大投票数");
            }
        } else if (EBoolean.YES.getCode().equals(attend.getType())) {
            // 判断教练投了多少票
            Long jlNum = voteBO.getTotalCount(EBoolean.YES.getCode(),
                req.getUserId(), null);
            if (jlNum >= StringValidater.toInteger(map.get("JLVOTE"))) {
                throw new BizException("xn0000", "抱歉,您今天对教练的投票数已经超过最大投票数");
            }
        }
        // 判断今天有没有给他投票
        Long count = voteBO.getTotalCount(attend.getType(), req.getUserId(),
            attend.getUserId());
        if (count >= StringValidater.toInteger(map.get("XTVOTE"))) {
            throw new BizException("xn0000", "您今天已经给他投过票了");
        }
        Vote data = new Vote();
        String code = OrderNoGenerater.generate(EPrefixCode.VOTE.getCode());
        data.setCode(code);
        data.setVoteType(attend.getType());
        data.setUserId(req.getUserId());
        data.setToUser(attend.getUserId());
        data.setVoteDatetime(new Date());
        voteBO.saveVote(data);
        attendBO.refreshAttend(attend);
        return code;
    }

    @Override
    public int editVote(Vote data) {
        if (!voteBO.isVoteExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return voteBO.refreshVote(data);
    }

    @Override
    public int dropVote(String code) {
        if (!voteBO.isVoteExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return voteBO.removeVote(code);
    }

    @Override
    public Paginable<Vote> queryVotePage(int start, int limit, Vote condition) {
        return voteBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Vote> queryVoteList(Vote condition) {
        return voteBO.queryVoteList(condition);
    }

    @Override
    public Vote getVote(String code) {
        return voteBO.getVote(code);
    }
}
