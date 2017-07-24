package com.std.gym.dto.req;

/**
 * 查询平均评分
 * @author: asus 
 * @since: 2017年7月19日 下午3:48:36 
 * @history:
 */
public class XN622147Req {
    // 私教编号
    private String coachCode;

    // 产品编号
    private String productCode;

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

}
