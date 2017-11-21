package com.std.gym.dto.req;

/**
 * 分页查询私教(front)（带有筛选）
 * @author: asus 
 * @since: 2017年7月18日 下午2:15:00 
 * @history:
 */
public class XN622093Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3807779672274861782L;

    // 类型(必填)
    private String type;

    // 私教用户
    private String userId;

    // 星级
    private String star;

    // 标签
    private String label;

    // 状态
    private String status;

    // UI位置
    private String location;

    // 审核人
    private String approver;

    // 上课周期
    private String skCycle;

    // 上课时间
    private String classDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getSkCycle() {
        return skCycle;
    }

    public void setSkCycle(String skCycle) {
        this.skCycle = skCycle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassDatetime() {
        return classDatetime;
    }

    public void setClassDatetime(String classDatetime) {
        this.classDatetime = classDatetime;
    }
}
