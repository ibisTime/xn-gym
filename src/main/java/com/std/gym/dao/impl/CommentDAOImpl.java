package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.ICommentDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.Comment;

@Repository("commentDAOImpl")
public class CommentDAOImpl extends AMybatisTemplate implements ICommentDAO {

    @Override
    public int insert(Comment data) {
        return super.insert(NAMESPACE.concat("insert_comment"), data);
    }

    @Override
    public int delete(Comment data) {
        return super.delete(NAMESPACE.concat("delete_comment"), data);
    }

    @Override
    public Comment select(Comment condition) {
        return super.select(NAMESPACE.concat("select_comment"), condition,
            Comment.class);
    }

    @Override
    public Long selectTotalCount(Comment condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_comment_count"),
            condition);
    }

    @Override
    public List<Comment> selectList(Comment condition) {
        return super.selectList(NAMESPACE.concat("select_comment"), condition,
            Comment.class);
    }

    @Override
    public List<Comment> selectList(Comment condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_comment"), start,
            count, condition, Comment.class);
    }

    @Override
    public int update(Comment data) {
        return super.update(NAMESPACE.concat("update_comment"), data);
    }

    @Override
    public int approveComment(Comment data) {
        return super.update(NAMESPACE.concat("update_approve"), data);
    }

}
