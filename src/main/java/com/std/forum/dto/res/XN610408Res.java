package com.std.forum.dto.res;

/**
 * 统计pv帖子用户 返回对象
 * @author William
 * @since  2017年5月16日 下午2:40:46
 * @history
 */
public class XN610408Res {
    // 公司编号
    private String companyCode;

    // 公司名称
    private String companyName;

    // 帖子数
    private Long postNum;

    // 用户数
    private Long userNum;

    // PV数
    private Long pageViewNum;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getPostNum() {
        return postNum;
    }

    public void setPostNum(Long postNum) {
        this.postNum = postNum;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Long getPageViewNum() {
        return pageViewNum;
    }

    public void setPageViewNum(Long pageViewNum) {
        this.pageViewNum = pageViewNum;
    }

}
