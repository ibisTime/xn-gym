package com.std.gym.domain;

import com.std.gym.dao.base.ABaseDO;

/**
* 评论
* @author: xieyj 
* @since: 2017-07-13 16:45:10
* @history:
*/
public class Comment extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 是否好评
    private String isPraise;

    // 好评分数
    private String scores;

    // 评论内容
    private String content;

    // 状态
    private String status;

    // 评论人
    private String commer;

    // 评论时间
    private String commentDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private String updateDatetime;

    // 备注
    private String remark;

    // 备注
    private String productCode;

    // (课程/私教)产品编号
    private String orderCode;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setIsPraise(String isPraise) {
        this.isPraise = isPraise;
    }

    public String getIsPraise() {
        return isPraise;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getScores() {
        return scores;
    }

    public void setCommer(String commer) {
        this.commer = commer;
    }

    public String getCommer() {
        return commer;
    }

    public void setCommentDatetime(String commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public String getCommentDatetime() {
        return commentDatetime;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
