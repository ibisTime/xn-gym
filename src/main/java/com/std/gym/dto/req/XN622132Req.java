package com.std.gym.dto.req;

import java.util.List;

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

    // 类型
    private String type;

    // 下单人
    private String applyUser;

    // 教练编号
    private String toUser;

    // 状态
    private String status;

    // 状态
    private List<String> statusList;

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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
