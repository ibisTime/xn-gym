package com.std.gym.bo;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Account;
import com.std.gym.dto.res.XN002501Res;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EChannelType;

/**
 * @author: xieyj
 * @since: 2016年11月11日 上午11:23:06 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    /**
     * 根据用户编号进行账户资金划转
     * @param fromUserId
     * @param toUserId
     * @param currency
     * @param amount
     * @param bizType
     * @param fromBizNote
     * @param toBizNote 
     * @create: 2017年3月26日 下午8:42:38 xieyj
     * @history:
     */
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            EChannelType channelType, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote);

    /**
     * 获取用户账户
     * @param userId
     * @param type
     * @return 
     * @create: 2017年4月1日 下午4:46:46 asus
     * @history:
     */
    public Long getAccountByUserId(String userId, EChannelType type);

    public XN002501Res doWeiXinH5PayRemote(String fromUserId,
            String fromOpenId, String toUserId, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String payGroup);

}
