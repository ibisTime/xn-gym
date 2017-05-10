package com.std.forum.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.std.forum.bo.IUserExtBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.domain.UserExt;
import com.std.forum.dto.req.XN805073Req;
import com.std.forum.dto.req.XN805074Req;
import com.std.forum.dto.req.XN805075Req;
import com.std.forum.dto.req.XN805150Req;
import com.std.forum.http.BizConnecter;
import com.std.forum.http.JsonUtils;

@Component
public class UserExtBOImpl extends PaginableBOImpl<UserExt> implements
        IUserExtBO {

    @Override
    public void refreshUserPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(photo)) {
            XN805073Req req = new XN805073Req();
            req.setUserId(userId);
            req.setPhoto(photo);
            BizConnecter.getBizData("805073", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public void refreshUserExt(String userId, String photo, String gender,
            String birthday, String email, String introduce) {
        XN805074Req req = new XN805074Req();
        req.setUserId(userId);
        req.setGender(gender);
        req.setBirthday(birthday);
        req.setPhoto(photo);
        req.setEmail(email);
        req.setIntroduce(introduce);
        BizConnecter.getBizData("805074", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void refreshNickname(String userId, String nickname) {
        XN805075Req req = new XN805075Req();
        req.setUserId(userId);
        req.setNickname(nickname);
        BizConnecter.getBizData("805075", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void refreshLoginName(String userId, String loginName) {
        XN805150Req req = new XN805150Req();
        req.setUserId(userId);
        req.setLoginName(loginName);
        BizConnecter.getBizData("805150", JsonUtils.object2Json(req),
            Object.class);
    }
}
