/**
 * @Title IUserBO.java 
 * @Package com.std.forum.bo 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午8:09:47 
 * @version V1.0   
 */
package com.std.gym.bo;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.User;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 下午8:09:47 
 * @history:
 */
public interface IUserBO extends IPaginableBO<User> {
    public User getRemoteUser(String userId);

    /**
     * 加减积分
     * @param userId
     * @param direction
     * @param amount
     * @param remark
     * @param refNo 
     * @create: 2016年10月12日 上午8:13:47 xieyj
     * @history:
     */
    public void doTransfer(String userId, String direction, Long amount,
            String remark, String refNo);

    /**
     * 用户间单账户划转
     * @param fromUser
     * @param toUser
     * @param amount
     * @param remark
     * @param refNo 
     * @create: 2016年10月24日 下午11:14:29 xieyj
     * @history:
     */
    public void doTransferAdd(String fromUser, String toUser, Long amount,
            String remark, String refNo);

    /**
     * 活动加减积分
     * @param userId
     * @param direction
     * @param ruleType
     * @param refNo 
     * @create: 2016年10月23日 下午8:54:46 xieyj
     * @history:
     */
    public void doTransfer(String userId, String direction, String ruleType,
            String refNo);

    /**
     * 更新用户等级
     * @param userId
     * @param level 
     * @create: 2017年4月1日 下午5:07:28 asus
     * @history:
     */
    public void upgradeLevel(String userId, String level);

    public String doAddUser(String mobile, String realName, String userReferee,
            String updater, String kind, String isRegHx, String systemCode);

    public String doLogin(String loginName, String loginPwd, String kind,
            String systemCode);
}
