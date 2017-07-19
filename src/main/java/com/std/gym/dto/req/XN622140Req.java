package com.std.gym.dto.req;

import java.util.List;

/**
 * 评论
 * @author: asus 
 * @since: 2017年7月19日 上午11:35:34 
 * @history:
 */
public class XN622140Req {
    // 内容
    private String content;

    // 项目
    private List<XN622200Req> itemScoreList;

    // 评论人
    private String commer;

    // 产品编号
    private String productCode;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<XN622200Req> getItemScoreList() {
        return itemScoreList;
    }

    public void setItemScoreList(List<XN622200Req> itemScoreList) {
        this.itemScoreList = itemScoreList;
    }
}
