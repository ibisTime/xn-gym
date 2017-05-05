package com.std.forum.dto.req;


/**
 * 修改评论标题
 * @author William
 *
 */
public class XN610123Req {
	
	//帖子编号（必填）
	private String code;
	
	//帖子新标题（必填）
	private String title ;
	
	 // 操作人 （必填）
    private String userId;
    
    //备注(修改原因)（选填）
    private String remark ;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
