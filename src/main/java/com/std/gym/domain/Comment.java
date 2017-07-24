package com.std.gym.domain;

import java.util.Date;
import java.util.List;

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

    // 得分
    private Integer score;

    // 评论内容
    private String content;

    // 状态
    private String status;

    // 评论人
    private String commer;

    // 评论时间
    private Date commentDatetime;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    // 产品编号
    private String productCode;

    // 教练编号
    private String coachCode;

    // **********db************
    // 评论人名称
    private String commerRealName;

    // 课程名称
    private String courseName;

    // 教练名称
    private String coachRealName;

    // 状态List
    private List<String> statusList;

    // 头像
    private String photo;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCommer(String commer) {
        this.commer = commer;
    }

    public String getCommer() {
        return commer;
    }

    public void setCommentDatetime(Date commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public Date getCommentDatetime() {
        return commentDatetime;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCoachCode() {
        return coachCode;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }

    public String getCommerRealName() {
        return commerRealName;
    }

    public void setCommerRealName(String commerRealName) {
        this.commerRealName = commerRealName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoachRealName() {
        return coachRealName;
    }

    public void setCoachRealName(String coachRealName) {
        this.coachRealName = coachRealName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
