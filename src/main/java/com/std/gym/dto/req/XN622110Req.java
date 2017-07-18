package com.std.gym.dto.req;

/**
 * 分页查询私课
 * @author: asus 
 * @since: 2017年7月18日 下午4:37:01 
 * @history:
 */
public class XN622110Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -6652693889950632131L;

    // 课程名称
    private String name;

    // 上课时间
    private String classDatetime;

    // UI位置
    private String location;

    // 状态
    private String status;

    // 私教编号
    private String coachCode;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoachCode() {
        return coachCode;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }
}
