package com.std.forum.dto.req;

import java.util.List;

/**
 * 锁帖/取消锁帖
 * @author: asus 
 * @since: 2017年3月22日 下午4:25:23 
 * @history:
 */
public class XN610119Req {

    // 帖子编号（必填）
    private List<String> codeList;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
