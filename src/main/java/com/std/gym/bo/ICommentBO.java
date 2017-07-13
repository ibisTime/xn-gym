package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.Comment;



 
public interface ICommentBO extends IPaginableBO<Comment> {


	public boolean isCommentExist(String code);


	public String saveComment(Comment data);


	public int removeComment(String code);


	public int refreshComment(Comment data);


	public List<Comment> queryCommentList(Comment condition);


	public Comment getComment(String code);


}