package com.std.gym.dto.req;

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

    // 私教编号
    private String coachCode;

    // 产品编号
    private String productCode;

    // 状态
    private String status;

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
}
