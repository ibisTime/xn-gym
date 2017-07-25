package com.std.gym.dto.req;

/**
 * 团课开始上课
 * @author: asus 
 * @since: 2017年7月17日 下午7:06:52 
 * @history:
 */
public class XN622056Req {
    // 编号
    private String code;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
