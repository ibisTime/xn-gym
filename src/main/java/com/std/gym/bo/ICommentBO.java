/**
 * @Title ICommentBO.java 
 * @Package com.std.forum.bo 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午1:21:32 
 * @version V1.0   
 */
package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Comment;
import com.std.gym.domain.User;

/** 
 * 评论
 * @author: xieyj 
 * @since: 2016年8月29日 下午1:21:32 
 * @history:
 */
public interface ICommentBO extends IPaginableBO<Comment> {

    public String saveComment(String content, String parentCode, String status,
            User user, String postCode);

    public int removeComment(String code);

    public int removeCommentByPost(String code);

    public int refreshCommentApprove(String code, String approveResult,
            String approver, String approveNote);

    public int refreshCommentReport(String code, String remark);

    public int refreshCommentReturn(String code);

    public Comment getComment(String code);

    public List<Comment> queryCommentList(Comment condition);

    public List<Comment> queryCommentList(String parentCode, String status);

    public List<Comment> queryCommentList(String postCode, String status,
            int limit);

    public long getCommentTotalCount(String postCode, String status);

    public List<Comment> queryCommentLimitList(String postCode);

    public List<Comment> selectMyList(Comment condition);

    public List<Comment> queryMyCommentList(Comment condition, int start,
            int limit);

    public List<Comment> selectTDList(Comment condition);

    public List<Comment> queryTDCommentList(Comment condition, int start,
            int limit);

    public void updateUserInf(String userId, String nickname, String photo);

    public void updateUserInf(String userId, String loginName);

}
