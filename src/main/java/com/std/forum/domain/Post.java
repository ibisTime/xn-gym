/**
 * @Title Post.java 
 * @Package com.std.forum.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年8月28日 下午8:23:00 
 * @version V1.0   
 */
package com.std.forum.domain;

import java.util.Date;
import java.util.List;

import com.std.forum.dao.base.ABaseDO;

/** 
 * 帖子
 * @author: xieyj 
 * @since: 2016年8月28日 下午8:23:00 
 * @history:
 */
public class Post extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 标题
    private String title;

    // 内容
    private String content;

    // 图片
    private String pic;

    // 状态(0 待审核 1 审核通过 2 审核不通过 3 已发布)
    private String status;

    // 发布人
    private String publisher;

    // 发布时间
    private Date publishDatetime;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 审核说明
    private String approveNote;

    // UI位置
    private String location;

    // UI序号
    private Integer orderNo;

    // 版块编号
    private String plateCode;

    // 是否锁帖
    private String isLock;

    // 评论数
    private Integer sumComment;

    // 点赞数
    private Integer sumLike;

    // 阅读数
    private Integer sumRead;

    // 打赏人数
    private Integer sumReward;

    // 备注
    private String remark;

    // *****************db properties ********************
    // 登录名
    private String loginName;

    // 昵称
    private String nickname;

    // 照片
    private String photo;

    // 发布时间起
    private Date publishDatetimeStart;

    // 发布时间止
    private Date publishDatetimeEnd;

    // 用户编号
    private String userId;

    // 用户编号
    private String talker;

    // 对于当前用户，该帖子是否被点赞
    private String isDZ;

    // 对于当前用户，该帖子是否被收藏
    private String isSC;

    // 用户类型
    private String type;

    // 点赞
    private List<PostTalk> likeList;

    // 评论
    private List<Comment> commentList;

    private Comment comment;

    // 站点
    private String companyCode;

    // 关键字查询
    private String keyword;

    // 图片数组
    private String[] picArr;

    // 版块名称
    private String plateName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Date publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public Date getPublishDatetimeStart() {
        return publishDatetimeStart;
    }

    public void setPublishDatetimeStart(Date publishDatetimeStart) {
        this.publishDatetimeStart = publishDatetimeStart;
    }

    public Date getPublishDatetimeEnd() {
        return publishDatetimeEnd;
    }

    public void setPublishDatetimeEnd(Date publishDatetimeEnd) {
        this.publishDatetimeEnd = publishDatetimeEnd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<PostTalk> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<PostTalk> likeList) {
        this.likeList = likeList;
    }

    public String getIsDZ() {
        return isDZ;
    }

    public void setIsDZ(String isDZ) {
        this.isDZ = isDZ;
    }

    public String getIsSC() {
        return isSC;
    }

    public void setIsSC(String isSC) {
        this.isSC = isSC;
    }

    public String getTalker() {
        return talker;
    }

    public void setTalker(String talker) {
        this.talker = talker;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String[] getPicArr() {
        return picArr;
    }

    public void setPicArr(String[] picArr) {
        this.picArr = picArr;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getSumComment() {
        return sumComment;
    }

    public void setSumComment(Integer sumComment) {
        this.sumComment = sumComment;
    }

    public Integer getSumLike() {
        return sumLike;
    }

    public void setSumLike(Integer sumLike) {
        this.sumLike = sumLike;
    }

    public Integer getSumRead() {
        return sumRead;
    }

    public void setSumRead(Integer sumRead) {
        this.sumRead = sumRead;
    }

    public Integer getSumReward() {
        return sumReward;
    }

    public void setSumReward(Integer sumReward) {
        this.sumReward = sumReward;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
