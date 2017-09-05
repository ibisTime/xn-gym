package com.std.gym.dto.req;

import java.util.List;

import com.std.gym.domain.SizeData;

/**
 * 填表
 * @author: asus 
 * @since: 2017年9月4日 下午9:10:26 
 * @history:
 */
public class XN622127Req {
    // 订单编号（必填）
    private String orderCode;

    // 体测数据（必填）
    private List<SizeData> sizeDataList;

    // 更新人（必填）
    private String updater;

    // 备注
    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<SizeData> getSizeDataList() {
        return sizeDataList;
    }

    public void setSizeDataList(List<SizeData> sizeDataList) {
        this.sizeDataList = sizeDataList;
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
