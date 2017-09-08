package com.std.gym.dto.req;

/**
 * 我要参与
 * @author: asus 
 * @since: 2017年9月7日 下午5:44:13 
 * @history:
 */
public class XN622221Req {
    // 活动编号（必填）
    private String activityCode;

    // 地址（必填）
    private String address;

    // 健身照（必填）
    private String advPic;

    // 年龄（必填）
    private String age;

    // 图文描述
    private String description;

    // 工作年限（必填）
    private String duration;

    // 性别（必填）
    private String gender;

    // 用户类别（必填）
    private String kind;

    // 标签
    private String label;

    // 手机号（必填）
    private String mobile;

    // 身份证/教练证（必填）
    private String pdf;

    // 缩率图（必填）
    private String pic;

    // 真实名字（必填）
    private String realName;

    // 推荐人
    private String userReferee;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

}
