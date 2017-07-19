package com.std.gym.dto.req;

/**
 * 审核评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:12:02 
 * @history:
 */
public class XN622142Req {
    // 编号
    private String code;

    // 审批结果
    private String result;

    // 更新人
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}
