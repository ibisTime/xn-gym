package com.std.gym.domain;

import java.util.Date;

import com.std.gym.dao.base.ABaseDO;

/**
* 私教
* @author: xieyj 
* @since: 2017-07-13 16:32:11
* @history:
*/
public class Coach extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 教练编号
    private String userId;

    // 年龄
    private String age;

    // 健身年限
    private String duration;

    // 特长
    private String strengths;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

    // 星级
    private String star;

    // 标签
    private String label;

    // 状态
    private String status;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStar() {
        return star;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
