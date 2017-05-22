package com.std.forum.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.ISplateBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.OrderNoGenerater;
import com.std.forum.dao.ISplateDAO;
import com.std.forum.domain.Splate;
import com.std.forum.dto.res.XN610049Res;
import com.std.forum.enums.EPrefixCode;
import com.std.forum.exception.BizException;

@Component
public class SplateBOImpl extends PaginableBOImpl<Splate> implements ISplateBO {

    @Autowired
    private ISplateDAO splateDAO;

    @Override
    public boolean isSplateExist(String code) {
        Splate condition = new Splate();
        condition.setCode(code);
        if (splateDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveSplate(String name, String parentCode, String pic,
            String orderNo, String userId, Integer isDetault,
            String companyCode, String status, String updater, String remark) {
        Splate data = new Splate();
        String code = OrderNoGenerater.generateME(EPrefixCode.SPLATE.getCode());
        data.setCode(code);
        data.setName(name);
        data.setBplateCode(parentCode);
        data.setPic(pic);
        data.setOrderNo(orderNo);
        data.setModerator(userId);
        data.setIsDefault(isDetault);
        data.setCompanyCode(companyCode);
        data.setStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        splateDAO.insert(data);
        return code;
    }

    @Override
    public int removeSplate(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Splate data = new Splate();
            data.setCode(code);
            count = splateDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSplate(String code, String name, String parentCode,
            String pic, String orderNo, String userId, Integer isDefault,
            String companyCode, String status, String updater, String remark,String description) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Splate data = new Splate();
            data.setCode(code);
            data.setName(name);
            data.setBplateCode(parentCode);
            data.setPic(pic);
            data.setOrderNo(orderNo);
            data.setModerator(userId);
            data.setIsDefault(isDefault);
            data.setCompanyCode(companyCode);
            data.setStatus(status);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            data.setDescription(description);
            count = splateDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Splate> querySplateList(Splate condition) {
        return splateDAO.selectList(condition);
    }

    @Override
    public Splate getSplate(String code) {
        Splate data = null;
        if (StringUtils.isNotBlank(code)) {
            Splate condition = new Splate();
            condition.setCode(code);
            data = splateDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "小版块不存在");
            }
        }
        return data;
    }

    @Override
    public List<Splate> querySplateList(String parentCode) {
        Splate condition = new Splate();
        condition.setBplateCode(parentCode);
        return splateDAO.selectList(condition);
    }

    @Override
    public List<Splate> getPlateByUserId(String userId) {
        Splate condition = new Splate();
        condition.setModerator(userId);
        return splateDAO.selectList(condition);
    }

    @Override
    public void defaultSplate(Splate splate, Integer isDetault, String updater) {
        splate.setIsDefault(isDetault);
        splate.setUpdater(updater);
        splate.setUpdateDatetime(new Date());
        splateDAO.defaultSplate(splate);
    }

    @Override
    public List<Splate> queryIsDefaultSplateList(Integer isDefault,
            String status, String companyCode) {
        Splate condition = new Splate();
        condition.setIsDefault(isDefault);
        condition.setStatus(status);
        condition.setCompanyCode(companyCode);
        return splateDAO.selectList(condition);
    }

	@Override
	public List<XN610049Res> querySplateDetailList(Splate condition) {
		// TODO Auto-generated method stub
		
		return splateDAO.selectDetailSplate(condition);
	}
}
