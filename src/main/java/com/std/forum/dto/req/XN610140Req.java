package com.std.forum.dto.req;

/**
 * 根据评论编号搜帖子详情
 * @author: xieyj 
 * @since: 2016年10月13日 下午2:10:14 
 * @history:
 */
public class XN610140Req {

    // 评论编号（必填）
    private String commentCode;

    // 用户编号（选填）
    private String userId;

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
