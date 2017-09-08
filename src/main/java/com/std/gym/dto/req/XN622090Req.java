package com.std.gym.dto.req;

/**
 * 私教申请
 * @author: asus 
 * @since: 2017年7月18日 上午11:12:21 
 * @history:
 */
public class XN622090Req {
    // 用户Id
    private String userId;

    // 真实姓名
    private String realName;

    // 身份证/教练照
    private String pdf;

    // 缩略图
    private String pic;

    // 地址
    private String address;

    // 性别（1 男，0 女）
    private String gender;

    // 年龄
    private String age;

    // 工作年限
    private String duration;

    // 标签
    private String label;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
}
