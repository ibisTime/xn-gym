package com.std.forum.ao;

import java.util.List;

import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Comment;
import com.std.forum.dto.res.XN610110Res;

/** 
 * 评论
 * @author: zuixian 
 * @since: 2016年9月19日 下午2:52:00 
 * @history:
 */
public interface ICommentAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /*
     * 发布评论
     */
    public XN610110Res doComment(String content, String parentCode,
            String commer);

    public Comment getComment(String code);

    public List<Comment> queryCommentList(Comment condition);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public Paginable<Comment> queryCommentMyPage(int start, int limit,
            Comment condition);

    public Paginable<Comment> queryMyCommentPage(int start, int limit,
            Comment condition);

    public Paginable<Comment> queryTDCommentPage(int start, int limit,
            Comment condition, String userId);

    public Paginable<Comment> queryOSSCommentPage(int start, int limit,
            Comment condition);

    public Comment getOSSComment(String code);
}
