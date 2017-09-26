package com.std.gym.domain;

import java.util.Date;

import com.std.gym.dao.base.ABaseDO;

/**
* 私课
* @author: xieyj 
* @since: 2017-07-13 16:38:13
* @history:
*/
public class PerCourse extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 上课周期
    private Integer skCycle;

    // 上课时间(时)
    private String skStartDatetime;

    // 下课时间(止)
    private String skEndDatetime;

    // 上课地址
    private String address;

    // 上课人数
    private Integer totalNum;

    // 缩略图
    private String pic;

    // 广告图
    private String advPic;

    // 描述
    private String description;

    // 价格
    private Long price;

    // UI位置
    private String location;

    // UI顺序
    private Integer orderNo;

    // 状态
    private String status;

    // 私教编号
    private String coachCode;

    // ************db***********
    // 上课时间
    private Date classDatetime;

    // 是否预订(0 未预定，1，以约定)
    private String isAppoint;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setSkCycle(Integer skCycle) {
        this.skCycle = skCycle;
    }

    public Integer getSkCycle() {
        return skCycle;
    }

    public void setSkStartDatetime(String skStartDatetime) {
        this.skStartDatetime = skStartDatetime;
    }

    public String getSkStartDatetime() {
        return skStartDatetime;
    }

    public void setSkEndDatetime(String skEndDatetime) {
        this.skEndDatetime = skEndDatetime;
    }

    public String getSkEndDatetime() {
        return skEndDatetime;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }

    public String getCoachCode() {
        return coachCode;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getClassDatetime() {
        return classDatetime;
    }

    public void setClassDatetime(Date classDatetime) {
        this.classDatetime = classDatetime;
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

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(String isAppoint) {
        this.isAppoint = isAppoint;
    }

}
