package com.std.gym.domain;

import java.util.Date;
import java.util.List;

import com.std.gym.dao.base.ABaseDO;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月9日 下午9:25:39 
 * @history:
 */
public class ActivityOrder extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 订单编号
    private String code;

    // 下单用户
    private String applyUser;

    // 商品编号（活动编号）
    private String productCode;

    // 商品名称
    private String productName;

    // 真实姓名
    private String realName;

    // 手机号
    private String mobile;

    // 身份证号
    private String idNo;

    // 下单时间
    private Date createDatetime;

    // 报名人数
    private Integer quantity;

    // 单价
    private Long unitPrice;

    // 订单总额
    private Long totalAmount;

    // 订单状态
    private String status;

    // 取消时间
    private Date cancelDatetime;

    // 支付组号
    private String payGroup;

    // 支付编号
    private String payCode;

    // 支付金额
    private Long payAmount;

    // 用户支付时间
    private Date payDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    // *************DB**************
    // 创建开始时间
    private Date createBeginDatetime;

    // 创建结束时间
    private Date createEndDatetime;

    // 支付开始时间
    private Date payBeginDatetime;

    // 支付结束时间
    private Date payEndDatetime;

    // 活动开始时间
    private Date activityBeginDatetime;

    // 活动结束时间
    private Date activityEndDatetime;

    // 活动图片
    private String pic;

    // 用户名
    private String nickname;

    // 公司名称
    private String companyName;

    // 状态List
    private List<String> statusList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCancelDatetime() {
        return cancelDatetime;
    }

    public void setCancelDatetime(Date cancelDatetime) {
        this.cancelDatetime = cancelDatetime;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateBeginDatetime() {
        return createBeginDatetime;
    }

    public void setCreateBeginDatetime(Date createBeginDatetime) {
        this.createBeginDatetime = createBeginDatetime;
    }

    public Date getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(Date createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

    public Date getPayBeginDatetime() {
        return payBeginDatetime;
    }

    public void setPayBeginDatetime(Date payBeginDatetime) {
        this.payBeginDatetime = payBeginDatetime;
    }

    public Date getPayEndDatetime() {
        return payEndDatetime;
    }

    public void setPayEndDatetime(Date payEndDatetime) {
        this.payEndDatetime = payEndDatetime;
    }

    public Date getActivityBeginDatetime() {
        return activityBeginDatetime;
    }

    public void setActivityBeginDatetime(Date activityBeginDatetime) {
        this.activityBeginDatetime = activityBeginDatetime;
    }

    public Date getActivityEndDatetime() {
        return activityEndDatetime;
    }

    public void setActivityEndDatetime(Date activityEndDatetime) {
        this.activityEndDatetime = activityEndDatetime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
