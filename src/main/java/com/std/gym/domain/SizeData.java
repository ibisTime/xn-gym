package com.std.gym.domain;

import com.std.gym.dao.base.ABaseDO;

/**
* 订单评价
* @author: shan 
* @since: 2017-09-04 20:33:05
* @history:
*/
public class SizeData extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private String id;

    // 用户编号
    private String userId;

    // 类型
    private String ckey;

    // 类型值
    private String cvalue;

    // 订单编号
    private String orderCode;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

}
