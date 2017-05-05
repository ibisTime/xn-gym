/**
 * @Title IPostAO.java 
 * @Package com.std.forum.ao 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午7:46:59 
 * @version V1.0   
 */
package com.std.forum.ao;

import java.util.List;

import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Post;
import com.std.forum.dto.res.XN610124Res;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 下午7:46:59 
 * @history:
 */
public interface IPostAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /*
     * 发帖
     */
    public String publishPost(String title, String content, String pic,
            String plateCode, String publisher, String isPublish);

    /*
     * 草稿发帖
     */
    public String draftPublishPost(String code, String title, String content,
            String pic, String plateCode, String publisher, String isPublish);

    /*
     * 删除帖子/评论
     */
    public void dropPost(String code, String userId, String type);

    /*
     * 批量删除帖子/评论
     */
    public void dropPostList(List<String> codeList, String userId, String type);

    /*
     * 批量审核帖子/评论
     */
    public void approvePostList(List<String> codeList, String approver,
            String approveResult, String approveNote, String type);

    /*
     * 还原帖子/评论
     */
    public void returnPost(List<String> codeList, String type);

    /*
     * 阅读帖子
     */
    public void readPost(String code);

    /*
     * 锁帖
     */
    public void lockPost(List<String> codeList);

    /*
     * 改版
     */
    public void editPostPlate(List<String> codeList, String plateCode);

    /*
     * 设置置顶，精华和头条
     */
    public void setPostLocation(String code, String location, Integer orderNo,
            String updater);

    //
    public Paginable<Post> queryPostPage(int start, int limit, Post condition);

    public List<Post> queryPostList(Post condition);

    public Post getPost(String code, String userId, String commStatus);

    public Paginable<Post> querySCPostPage(int start, int limit, Post condition);

    public List<Post> querySCPostList(String userId);

    public Post getPostByCommentCode(String commentCode, String userId);

    public Long getMyPostCount(String userId, String status);

    public Paginable<Post> queryTDPostPage(int start, int limit,
            Post condition, String userId);

    public Post getPost(String code);

    public Paginable<Post> queryOSSPostPage(int start, int limit, Post condition);

    public void updateTitle(String code, String title, String userId,
            String remark);

    public XN610124Res getTotal(String companyCode);
}
