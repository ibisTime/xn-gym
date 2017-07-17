package com.std.gym.bo;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Account;
import com.std.gym.dto.res.XN002501Res;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EChannelType;
import com.std.gym.enums.ECurrency;

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
     * @param refNo
     * @create: 2017年3月26日 下午8:42:38 xieyj
     * @history:
     */
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String refNo);

    /**
     * 获取用户账户
     * @param userId
     * @param type
     * @return 
     * @create: 2017年4月1日 下午4:46:46 asus
     * @history:
     */
    public Long getAccountByUserId(String userId, EChannelType type);

    /**
     * 根据用户编号和币种获取账户
     * @param userId
     * @param currency
     * @return 
     * @create: 2017年3月23日 下午12:02:11 myb858
     * @history:
     */
    public Account getRemoteAccount(String userId, ECurrency currency);

    public XN002501Res doWeiXinH5PayRemote(String fromUserId,
            String fromOpenId, String toUserId, String payGroup, String refNo,
            EBizType bizType, String fromBizNote, Long amount,
            String toBizNote, String backUrl);

}
