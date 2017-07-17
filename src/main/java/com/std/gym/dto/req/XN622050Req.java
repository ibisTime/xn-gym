package com.std.gym.dto.req;

/**
 * 新增团课
 * @author: asus 
 * @since: 2017年7月17日 下午5:15:03 
 * @history:
 */
public class XN622050Req {
    // 教练编号
    private String coachUser;

    // 课程名称
    private String name;

    // 上课时间
    private String classDatetime;

    // 上课时间
    private String skStartDatetime;

    // 下课时间
    private String skEndDatetime;

    // 总人数
    private String totalNum;

    // 地址
    private String address;

    // 联系方式
    private String contact;

    // 缩略图
    private String pic;

    // 广告图
    private String advPic;

    // 价格
    private String price;

    // 图文描述
    private String description;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getCoachUser() {
        return coachUser;
    }

    public void setCoachUser(String coachUser) {
        this.coachUser = coachUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassDatetime() {
        return classDatetime;
    }

    public void setClassDatetime(String classDatetime) {
        this.classDatetime = classDatetime;
    }

    public String getSkStartDatetime() {
        return skStartDatetime;
    }

    public void setSkStartDatetime(String skStartDatetime) {
        this.skStartDatetime = skStartDatetime;
    }

    public String getSkEndDatetime() {
        return skEndDatetime;
    }

    public void setSkEndDatetime(String skEndDatetime) {
        this.skEndDatetime = skEndDatetime;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
