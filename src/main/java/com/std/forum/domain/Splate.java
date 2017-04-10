package com.std.forum.domain;

import java.util.Date;

import com.std.forum.dao.base.ABaseDO;

/**
* 小板块
* @author: shan
* @since: 2017年03月20日
* @history:
*/
public class Splate extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 大板块编号
    private String bplateCode;

    // 图片
    private String pic;

    // 序号
    private String orderNo;

    // 版主编号
    private String moderator;

    // 站点编号
    private String companyCode;

    // 状态(1 可见 0 不可见)
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // *****************DB****************
    // 昵称
    private String nickname;

    // 手机号
    private String mobile;

    // 评论数
    private Integer allCommentNum;

    // 点赞数
    private Integer allLikeNum;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBplateCode(String bplateCode) {
        this.bplateCode = bplateCode;
    }

    public String getBplateCode() {
        return bplateCode;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    public String getModerator() {
        return moderator;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAllCommentNum() {
        return allCommentNum;
    }

    public void setAllCommentNum(Integer allCommentNum) {
        this.allCommentNum = allCommentNum;
    }

    public Integer getAllLikeNum() {
        return allLikeNum;
    }

    public void setAllLikeNum(Integer allLikeNum) {
        this.allLikeNum = allLikeNum;
    }

}
