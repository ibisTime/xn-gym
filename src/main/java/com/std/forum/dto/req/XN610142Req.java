package com.std.forum.dto.req;

/**
 * 点赞列表查
 * @author: asus 
 * @since: 2017年3月27日 下午1:52:37 
 * @history:
 */
public class XN610142Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 帖子编号
    private String postCode;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
