/**
 * @Title PostDAOImpl.java 
 * @Package com.std.forum.dao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月28日 下午9:46:01 
 * @version V1.0   
 */
package com.std.forum.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.forum.dao.IPostDAO;
import com.std.forum.dao.base.support.AMybatisTemplate;
import com.std.forum.domain.Post;

/** 
 * @author: xieyj 
 * @since: 2016年8月28日 下午9:46:01 
 * @history:
 */
@Repository("postDAOImpl")
public class PostDAOImpl extends AMybatisTemplate implements IPostDAO {

    @Override
    public int insert(Post data) {
        return super.insert(NAMESPACE.concat("insert_post"), data);
    }

    @Override
    public int delete(Post data) {
        return super.delete(NAMESPACE.concat("delete_post"), data);
    }

    /** 详情查
     * @see com.std.forum.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Post select(Post condition) {
        return (Post) super.select(NAMESPACE.concat("select_post_details"),
            condition, Post.class);
    }

    /** 列表查
     * @see com.std.forum.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Post> selectList(Post condition) {
        return super.selectList(NAMESPACE.concat("select_post_list"),
            condition, Post.class);
    }

    /** 分页查
     * @see com.std.forum.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Post> selectList(Post condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_post"), start, count,
            condition, Post.class);
    }

    /** 
     * @see com.std.forum.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public Long selectTotalCount(Post condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_post_count"),
            condition);
    }

    /** 查询小版块统计数
     * @see com.std.forum.dao.IPostDAO#selectPostNum(com.std.forum.domain.Post)
     */
    @Override
    public long selectPostNum(Post condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_post1_count"),
            condition);
    }

    // 列表查询收藏
    @Override
    public List<Post> selectSCList(Post condition) {
        return super.selectList(NAMESPACE.concat("select_post_sc"), condition,
            Post.class);
    }

    // 分页查询收藏
    @Override
    public List<Post> selectSCList(Post condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_post_sc"), start,
            limit, condition, Post.class);
    }

    @Override
    public int updateApprove(Post data) {
        return super.update(NAMESPACE.concat("update_post_approve"), data);
    }

    @Override
    public int updateLocation(Post data) {
        return super.update(NAMESPACE.concat("update_post_location"), data);
    }

    @Override
    public int updateLock(Post data) {
        return super.update(NAMESPACE.concat("update_post_lock"), data);
    }

    @Override
    public int updatePlate(Post data) {
        return super.update(NAMESPACE.concat("update_post_plate"), data);
    }

    /** 
     * @see com.std.forum.dao.IPostDAO#update(com.std.forum.domain.Post)
     */
    @Override
    public int update(Post data) {
        return super.update(NAMESPACE.concat("update_post"), data);
    }

    @Override
    public int updateStatus(Post data) {
        return super.update(NAMESPACE.concat("update_post_status"), data);
    }

    @Override
    public int updateSumReward(Post data) {
        return super.update(NAMESPACE.concat("update_sum_reward"), data);
    }

    @Override
    public int updateSumRead(Post data) {
        return super.update(NAMESPACE.concat("update_sum_read"), data);
    }

    @Override
    public int updateSumLike(Post data) {
        return super.update(NAMESPACE.concat("update_sum_like"), data);
    }

    @Override
    public int updateSumComment(Post data) {
        return super.update(NAMESPACE.concat("update_sum_comment"), data);
    }

    @Override
    public Long getMyPostCount(Post condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_getMyPostCount"), condition);
    }

    // 提到我的列表查
    @Override
    public List<Post> selectTDList(Post condition) {
        return super.selectList(NAMESPACE.concat("select_post_td"), condition,
            Post.class);
    }

    // 提到我的分页查
    @Override
    public List<Post> queryTDPostList(Post condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_post_td"), start,
            limit, condition, Post.class);
    }

    @Override
    public Post selectMaxRead(Post condition) {
        return super.select(NAMESPACE.concat("select_max_read"), condition,
            Post.class);
    }

    @Override
    public Post selectSumRead(Post condition) {
        return super.select(NAMESPACE.concat("select_sum_read"), condition,
            Post.class);
    }

    // 修改标题
    @Override
    public int updateTitle(Post data) {
        return super.update(NAMESPACE.concat("update_title"), data);
    }
}
