package com.xnjr.gym.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.gym.dao.ICommentDAO;
import com.std.gym.domain.Comment;
import com.xnjr.gym.base.ADAOTest;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 上午11:01:50 
 * @history:
 */
public class CommentDAOTest extends ADAOTest {
    @SpringBeanByType
    private ICommentDAO commentDAO;

    @Test
    public void selectList() {
        Comment condition = new Comment();
        List<Comment> list = commentDAO.selectList(condition);
        logger.info("select : {}", list.size());
    }
}
