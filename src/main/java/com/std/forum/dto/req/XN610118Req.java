package com.std.forum.dto.req;

import java.util.List;

public class XN610118Req {

    // 帖子编号（必填）
    private List<String> codeList;

    // 版块编号（必填）
    private String plateCode;

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
