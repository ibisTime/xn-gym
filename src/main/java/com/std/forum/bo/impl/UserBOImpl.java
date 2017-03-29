package com.std.forum.bo.impl;

import org.springframework.stereotype.Component;

import com.std.forum.bo.IUserBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.domain.User;
import com.std.forum.dto.req.XN001400Req;
import com.std.forum.dto.req.XN805300Req;
import com.std.forum.dto.req.XN805301Req;
import com.std.forum.dto.req.XN805302Req;
import com.std.forum.dto.res.XN001400Res;
import com.std.forum.exception.BizException;
import com.std.forum.http.BizConnecter;
import com.std.forum.http.JsonUtils;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:30 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {
    @Override
    public User getRemoteUser(String userId) {
        XN001400Req req = new XN001400Req();
        req.setTokenId(userId);
        req.setUserId(userId);
        XN001400Res res = BizConnecter.getBizData("001400",
            JsonUtils.object2Json(req), XN001400Res.class);
        if (res == null) {
            throw new BizException("XN000000", "编号为" + userId + "的用户不存在");
        }
        User user = new User();
        user.setUserId(res.getUserId());
        user.setLoginName(res.getLoginName());
        user.setNickname(res.getNickname());
        user.setPhoto(res.getPhoto());
        user.setMobile(res.getMobile());
        user.setIdentityFlag(res.getIdentityFlag());
        user.setUserReferee(res.getUserReferee());
        user.setLevel(res.getLevel());
        return user;
    }

    /*
     * @Override public XN001400Res getRemoteUser(String userId) { XN001400Req
     * req = new XN001400Req(); req.setTokenId(userId); req.setUserId(userId);
     * XN001400Res res = BizConnecter.getBizData("001400",
     * JsonUtils.object2Json(req), XN001400Res.class); if (res == null) { throw
     * new BizException("XN000000", "编号为" + userId + "的用户不存在"); } return res; }
     */

    /** 
     * @see com.std.forum.bo.IUserBO#doTransfer(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransfer(String userId, String direction, Long amount,
            String remark, String refNo) {
        XN805300Req req = new XN805300Req();
        req.setUserId(userId);
        req.setDirection(direction);
        req.setAmount(String.valueOf(amount));
        req.setRemark(remark);
        req.setRefNo(refNo);
        BizConnecter.getBizData("805300", JsonUtils.object2Json(req),
            Object.class);
    }

    /** 
     * @see com.std.forum.bo.IUserBO#doTransfer(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAdd(String fromUser, String toUser, Long amount,
            String remark, String refNo) {
        XN805301Req req = new XN805301Req();
        req.setFromUser(fromUser);
        req.setToUser(toUser);
        req.setAmount(String.valueOf(amount));
        req.setRemark(remark);
        req.setRefNo(refNo);
        BizConnecter.getBizData("805301", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void doTransfer(String userId, String direction, String ruleType,
            String refNo) {
        XN805302Req req = new XN805302Req();
        req.setUserId(userId);
        req.setDirection(direction);
        req.setRuleType(ruleType);
        req.setRefNo(refNo);
        BizConnecter.getBizData("805302", JsonUtils.object2Json(req),
            Object.class);
    }
}
