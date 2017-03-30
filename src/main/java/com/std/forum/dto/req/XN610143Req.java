package com.std.forum.dto.req;

/**
 * 提到我的评论分页查
 * @author: asus 
 * @since: 2017年3月30日 上午10:03:40 
 * @history:
 */
public class XN610143Req extends APageReq {

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
