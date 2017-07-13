package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ICommentAO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.Comment;
import com.std.gym.exception.BizException;



 
@Service
public class CommentAOImpl implements ICommentAO {

	@Autowired
	private ICommentBO commentBO;

	@Override
	public String addComment(Comment data) {
		return commentBO.saveComment(data);
	}

	@Override
	public int editComment(Comment data) {
		if (!commentBO.isCommentExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return commentBO.refreshComment(data);
	}

	@Override
	public int dropComment(String code) {
		if (!commentBO.isCommentExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return commentBO.removeComment(code);
	}

	@Override
	public Paginable<Comment> queryCommentPage(int start, int limit,
			Comment condition) {
		return commentBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<Comment> queryCommentList(Comment condition) {
		return commentBO.queryCommentList(condition);
	}

	@Override
	public Comment getComment(String code) {
		return commentBO.getComment(code);
	}
}