package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Comment;
import com.std.gym.dto.req.XN622200Req;

@Component
public interface ICommentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addComment(String content, List<XN622200Req> itemScoreList,
            String commer, String orderCode);

    public void dropComment(String code);

    public void approveComment(String code, String result, String approver,
            String remark);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
