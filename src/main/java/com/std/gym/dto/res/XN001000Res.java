package com.std.gym.dto.res;

public class XN001000Res {
    // 应用用户的唯一编号（目前暂用UserId,后期改进）
    private String tokenId;

    private String userId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
