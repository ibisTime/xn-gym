package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Comment;

@Component
public interface ICommentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addComment(Comment data);

    public int dropComment(String code);

    public int editComment(Comment data);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
