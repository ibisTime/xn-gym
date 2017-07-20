package com.std.gym.dto.req;

import java.util.List;

/**
 * 评论
 * @author: asus 
 * @since: 2017年7月19日 上午11:35:34 
 * @history:
 */
public class XN622140Req {

    // 内容（必填）
    private String content;

    // 项目（选填）
    private List<XN622200Req> itemScoreList;

    // 评论人（必填）
    private String commer;

    // 订单编号（必填）
    private String orderCode;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommer() {
        return commer;
    }

    public void setCommer(String commer) {
        this.commer = commer;
    }

    public List<XN622200Req> getItemScoreList() {
        return itemScoreList;
    }

    public void setItemScoreList(List<XN622200Req> itemScoreList) {
        this.itemScoreList = itemScoreList;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
