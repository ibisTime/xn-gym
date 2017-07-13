package com.std.gym.domain;

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

    // 缩略图
    private String pic;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

    // 星级
    private String star;

    // 标签
    private String label;

    // UI位置
    private String location;

    // UI顺序
    private String orderNo;

    // 价格（元/时）
    private String price;

    // 评论数
    private String sumCom;

    // 状态
    private String status;

    private String updater;

    private String updateDatetime;

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

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setSumCom(String sumCom) {
        this.sumCom = sumCom;
    }

    public String getSumCom() {
        return sumCom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
