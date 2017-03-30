package com.std.forum.dto.req;

/**
 * 提到我的帖子分页查
 * @author: asus 
 * @since: 2017年3月30日 上午10:55:09 
 * @history:
 */
public class XN610144Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 用户ID
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
