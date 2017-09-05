package com.std.gym.dto.req;

/**
 * 分页查询私教(oss)
 * @author: asus 
 * @since: 2017年7月18日 下午1:43:32 
 * @history:
 */
public class XN622095Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8526218853712044125L;

    // 类型(必填)
    private String type;

    // 用户编号（选填）
    private String userId;;

    // 星级（选填）
    private String star;

    // 标签（选填）
    private String label;

    // 状态（选填）
    private String status;

    // UI位置（选填）
    private String location;

    // 状态（选填）
    private String mobile;

    // 审核人（选填）
    private String approver;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
