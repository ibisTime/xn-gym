package com.std.gym.dto.req;

import java.util.List;

/**
 * 分页查询评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:38:09 
 * @history:
 */
public class XN622145Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3447659632595280080L;

    // 私教编号（选填）
    private String coachCode;

    // 产品编号（选填）
    private String productCode;

    // 状态（选填）
    private String status;

    // 内容（选填）
    private String content;

    // 状态List（选填）
    private List<String> statusList;

    public String getCoachCode() {
        return coachCode;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
