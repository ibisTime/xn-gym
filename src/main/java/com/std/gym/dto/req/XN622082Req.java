package com.std.gym.dto.req;

/**
 * 分页查询我的团课订单
 * @author: asus 
 * @since: 2017年7月18日 上午10:52:05 
 * @history:
 */
public class XN622082Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1850376942888552962L;

    // 下单人
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
