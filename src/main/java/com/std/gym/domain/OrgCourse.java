package com.std.gym.domain;

import java.util.Date;

import com.std.gym.dao.base.ABaseDO;

/**
* 团课
* @author: xieyj 
* @since: 2017-07-13 16:21:57
* @history:
*/
public class OrgCourse extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 任课教练
    private String coachUser;

    // 课程名称
    private String name;

    // 上课开始时间（起）
    private Date skStartDatetime;

    // 上课结束时间（止）
    private Date skEndDatetime;

    // 总人数
    private Integer totalNum;

    // 剩余人数
    private Integer remainNum;

    // 省
    private String province;

    // 市区
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 联系电话
    private String contact;

    // 缩略图
    private String pic;

    // 广告图
    private String advPic;

    // 图文详情
    private String description;

    // 价格
    private Long price;

    // UI位置
    private String location;

    // UI顺序
    private Integer orderNo;

    // 评论数
    private Integer sumCom;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ************db**************
    // 上课开始时间
    private Date classDatetime;

    // 开始上课时间（起）
    private Date beginClassDatetime;

    // 开始上课时间（止）
    private Date endClassDatetime;

    // 教练真实姓名
    private String realName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCoachUser(String coachUser) {
        this.coachUser = coachUser;
    }

    public String getCoachUser() {
        return coachUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSkStartDatetime(Date skStartDatetime) {
        this.skStartDatetime = skStartDatetime;
    }

    public Date getSkStartDatetime() {
        return skStartDatetime;
    }

    public void setSkEndDatetime(Date skEndDatetime) {
        this.skEndDatetime = skEndDatetime;
    }

    public Date getSkEndDatetime() {
        return skEndDatetime;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
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

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
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

    public Date getBeginClassDatetime() {
        return beginClassDatetime;
    }

    public void setBeginClassDatetime(Date beginClassDatetime) {
        this.beginClassDatetime = beginClassDatetime;
    }

    public Date getEndClassDatetime() {
        return endClassDatetime;
    }

    public void setEndClassDatetime(Date endClassDatetime) {
        this.endClassDatetime = endClassDatetime;
    }

    public Integer getSumCom() {
        return sumCom;
    }

    public void setSumCom(Integer sumCom) {
        this.sumCom = sumCom;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getClassDatetime() {
        return classDatetime;
    }

    public void setClassDatetime(Date classDatetime) {
        this.classDatetime = classDatetime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
