package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Comment;

public interface ICommentBO extends IPaginableBO<Comment> {

    public boolean isCommentExist(String code);

    public void saveComment(Comment data);

    public void removeComment(String code);

    public void approveComment(Comment data);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

    public List<Comment> queryCommentList(String coachCode, String productCode);

    public void approveComment(Comment data, String status, String approver,
            String remark);

}
