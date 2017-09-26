
package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.IAttendAO;
import com.std.gym.bo.IActivityBO;
import com.std.gym.bo.IAttendBO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.IVoteBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Activity;
import com.std.gym.domain.Attend;
import com.std.gym.domain.Coach;
import com.std.gym.enums.EActivityStatus;
import com.std.gym.enums.EBoolean;
import com.std.gym.enums.ESystemCode;
import com.std.gym.exception.BizException;

@Service
public class AttendAOImpl implements IAttendAO {

    @Autowired
    private IAttendBO attendBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IVoteBO voteBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IActivityBO activityBO;

    @Override
    public String addAttend(String loginName, String loginPwd, String kind,
            String activityCode) {
        String userId = userBO.doLogin(loginName, loginPwd, kind,
            ESystemCode.SYSTEM_CODE.getCode());
        Coach coach = coachBO.getCoachByUserId(userId);
        Activity activity = activityBO.getActivity(activityCode);

        if (activity.getEndDatetime().before(new Date())
                || !EActivityStatus.ONLINE.getCode().equals(
                    activity.getStatus())) {
            throw new BizException("xn0000", "活动已经结束");
        }
        List<Attend> attendList = attendBO
            .queryAttendList(userId, activityCode);
        if (CollectionUtils.isNotEmpty(attendList)) {
            throw new BizException("xn0000", "您已经报名参加了");
        }
        Long count = attendBO.getTotalCount(coach.getType(), activityCode);
        Integer number = count.intValue();
        return attendBO.saveAttend(coach, activity, number);
    }

    @Override
    public void editAttend(Attend data) {
        attendBO.refreshAttend(data);
    }

    @Override
    public Paginable<Attend> queryAttendPage(int start, int limit,
            Attend condition, String userId) {
        Paginable<Attend> page = attendBO.getPaginable(start, limit, condition);
        List<Attend> list = page.getList();
        for (Attend attend : list) {
            this.fullAttend(attend, userId);
        }
        return page;
    }

    @Override
    public List<Attend> queryAttendList(Attend condition, String userId) {
        List<Attend> list = attendBO.queryAttendList(condition);
        for (Attend attend : list) {
            this.fullAttend(attend, userId);
        }
        return list;
    }

    private void fullAttend(Attend attend, String userId) {
        Activity activity = activityBO.getActivity(attend.getActivityCode());
        attend.setTitle(activity.getTitle());
        Coach coach = coachBO.getCoach(attend.getCoachCode());
        attend.setCoach(coach);
        attend.setIsVote(EBoolean.NO.getCode());
        if (StringUtils.isNotBlank(userId)) {
            Long count = voteBO.getTotalCount(attend.getType(), userId,
                attend.getUserId());
            if (count > 0) {
                attend.setIsVote(EBoolean.YES.getCode());
            }
        }
    }

    @Override
    public Attend getAttend(String code, String userId) {
        Attend attend = attendBO.getAttend(code);
        this.fullAttend(attend, userId);
        return attend;
    }
}
