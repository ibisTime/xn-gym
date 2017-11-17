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

    // 类型（0 教练，1达人）
    private String type;

    // 教练编号
    private String userId;

    // 真实姓名
    private String realName;

    // 缩略图
    private String pic;

    private String idPhoto;

    // 身份证/教练证
    private String pdf;

    // 性别
    private String gender;

    // 年龄
    private Integer age;

    // 健身年限
    private Integer duration;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

    // 星级
    private Integer star;

    // 星数
    private Integer starNum;

    // 标签
    private String label;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 地址
    private String address;

    // 授课数量
    private Integer teachNum;

    // UI位置
    private String location;

    // UI顺序
    private Integer orderNo;

    // 状态
    private String status;

    // 评论数
    private Integer sumCom;

    // 审核人
    private String approver;

    // 信用额
    private Long creditAmount;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    // *************db**********
    // 上课周期
    private String skCycle;

    // 手机号
    private String mobile;

    // 用户状态
    private String uStatus;

    // 登录名
    private String loginName;

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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
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

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getStar() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStarNum() {
        return starNum;
    }

    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSkCycle() {
        return skCycle;
    }

    public void setSkCycle(String skCycle) {
        this.skCycle = skCycle;
    }

    public Integer getSumCom() {
        return sumCom;
    }

    public void setSumCom(Integer sumCom) {
        this.sumCom = sumCom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getuStatus() {
        return uStatus;
    }

    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTeachNum() {
        return teachNum;
    }

    public void setTeachNum(Integer teachNum) {
        this.teachNum = teachNum;
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

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

}
