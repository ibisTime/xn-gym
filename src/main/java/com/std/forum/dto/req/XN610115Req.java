package com.std.forum.dto.req;

import java.util.List;

public class XN610115Req {

    // 帖子/评论编号（必填）
    private List<String> codeList;

    // 类型(必填)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
