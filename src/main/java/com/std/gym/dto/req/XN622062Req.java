package com.std.gym.dto.req;

/**
 * 列表查询团课
 * @author: asus 
 * @since: 2017年7月17日 下午7:41:06 
 * @history:
 */
public class XN622062Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2210097397587858305L;

    // 任课教练
    private String coachUser;

    // 课程名称
    private String name;

    // 地址
    private String address;

    // 联系方式
    private String contact;

    // UI位置
    private String location;

    // 开始上课时间（起）
    private String beginClassDatetime;

    // 开始上课时间（止）
    private String endClassDatetime;

    // 上课时间
    private String classDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBeginClassDatetime() {
        return beginClassDatetime;
    }

    public void setBeginClassDatetime(String beginClassDatetime) {
        this.beginClassDatetime = beginClassDatetime;
    }

    public String getEndClassDatetime() {
        return endClassDatetime;
    }

    public void setEndClassDatetime(String endClassDatetime) {
        this.endClassDatetime = endClassDatetime;
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

    public String getClassDatetime() {
        return classDatetime;
    }

    public void setClassDatetime(String classDatetime) {
        this.classDatetime = classDatetime;
    }
}
