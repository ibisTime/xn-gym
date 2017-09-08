package com.std.gym.domain;

import java.util.Date;

import com.std.gym.dao.base.ABaseDO;

/**
* 投票记录
* @author: shan 
* @since: 2017-09-07 12:05:01
* @history:
*/
public class Vote extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private String code;

    // 投票人
    private String userId;

    // 被投人
    private String toUser;

    // 投票类型
    private String voteType;

    // 投票时间
    private Date voteDatetime;

    // -------------------db----------------------
    // 投票开始时间
    private Date createVoteDatetime;

    // 投票结束时间
    private Date endVoteDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public String getVoteType() {
        return voteType;
    }

    public Date getCreateVoteDatetime() {
        return createVoteDatetime;
    }

    public void setCreateVoteDatetime(Date createVoteDatetime) {
        this.createVoteDatetime = createVoteDatetime;
    }

    public Date getEndVoteDatetime() {
        return endVoteDatetime;
    }

    public void setEndVoteDatetime(Date endVoteDatetime) {
        this.endVoteDatetime = endVoteDatetime;
    }

    public void setVoteDatetime(Date voteDatetime) {
        this.voteDatetime = voteDatetime;
    }

    public Date getVoteDatetime() {
        return voteDatetime;
    }

}
