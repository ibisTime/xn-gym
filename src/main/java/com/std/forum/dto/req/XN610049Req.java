package com.std.forum.dto.req;

import java.util.List;

/**
 * 列表查询小版块(PC专用)
 * @author William
 * @since  2017年5月19日 上午11:53:58
 * @history
 */
public class XN610049Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 名称
    private String name;

    // 大板块
    private String parentCode;

    // 状态
    private String status;
    
    private List<String> statusList ;
    
   
    

    // 站点编号
    private String companyCode;

    // 版主
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	

	

	
    
    
    
    
}
