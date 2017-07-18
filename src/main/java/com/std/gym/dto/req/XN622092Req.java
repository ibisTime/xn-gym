package com.std.gym.dto.req;

/**
 * 审核私教信息
 * @author: asus 
 * @since: 2017年7月18日 下午1:11:13 
 * @history:
 */
public class XN622092Req {
    // 编号
    private String code;

    // 审批结果（1 通过，0不通过）
    private String result;

    // 审批人
    private String approver;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
