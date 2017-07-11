package com.std.gym.ao;

import java.util.List;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Comment;

/** 
 * 评论
 * @author: zuixian 
 * @since: 2016年9月19日 下午2:52:00 
 * @history:
 */
public interface ICommentAO {
    String DEFAULT_ORDER_COLUMN = "code";

    public String doComment(String content, String parentCode, String commer);

    public Comment getComment(String code);

    public List<Comment> queryCommentList(Comment condition);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

}
