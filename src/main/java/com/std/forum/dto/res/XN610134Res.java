package com.std.forum.dto.res;

import com.std.forum.domain.Comment;
import com.std.forum.domain.Post;

/**
 * 评论详情查
 * @author: asus 
 * @since: 2017年3月22日 下午5:41:17 
 * @history:
 */
public class XN610134Res {
    // 评论
    private Comment comment;

    // 帖子
    private Post post;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
