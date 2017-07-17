package com.std.gym.dto.req;

/**
 * 分页查询我的活动订单
 * @author: asus 
 * @since: 2017年7月17日 下午3:37:09 
 * @history:
 */
public class XN622042Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8482767987923651576L;

    // 申请人
    private String applyUser;

    // 状态
    private String status;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
