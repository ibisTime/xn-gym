package com.std.gym.dto.req;

/**
 * 分页查询我的私课订单
 * @author: asus 
 * @since: 2017年7月18日 下午8:27:34 
 * @history:
 */
public class XN622132Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2065073473356586943L;

    // 下单人
    private String applyUser;

    // 教练编号
    private String toUser;

    // 状态
    private String status;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
