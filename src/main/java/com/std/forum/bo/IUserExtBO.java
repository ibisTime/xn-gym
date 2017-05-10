package com.std.forum.bo;

import com.std.forum.bo.base.IPaginableBO;
import com.std.forum.domain.UserExt;

public interface IUserExtBO extends IPaginableBO<UserExt> {

    public void refreshUserPhoto(String userId, String photo);

    public void refreshUserExt(String userId, String photo, String gender,
            String birthday, String email, String introduce);

    public void refreshNickname(String userId, String nickname);

    public void refreshLoginName(String userId, String loginName);
}
