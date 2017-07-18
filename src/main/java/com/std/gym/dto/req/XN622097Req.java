package com.std.gym.dto.req;

/**
 * 列表查询私教
 * @author: asus 
 * @since: 2017年7月18日 下午1:43:32 
 * @history:
 */
public class XN622097Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8526218853712044125L;

    // 用户编号
    private String userId;;

    // 星级
    private String star;

    // 标签
    private String label;

    // 状态
    private String status;

    // 审核人
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
}
