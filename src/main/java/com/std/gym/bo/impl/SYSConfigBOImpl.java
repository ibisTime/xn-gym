package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.ISYSConfigDAO;
import com.std.gym.domain.SYSConfig;
import com.std.gym.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2017年4月23日 下午6:19:30 
 * @history:
 */
@Component
public class SYSConfigBOImpl extends PaginableBOImpl<SYSConfig> implements
        ISYSConfigBO {
    @Autowired
    private ISYSConfigDAO sysConfigDAO;

    @Override
    public int refreshSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = sysConfigDAO.updateValue(data);
        }
        return count;
    }

    @Override
    public SYSConfig getConfig(Long id) {
        SYSConfig sysConfig = null;
        if (id != null) {
            SYSConfig condition = new SYSConfig();
            condition.setId(id);
            sysConfig = sysConfigDAO.select(condition);
        }
        return sysConfig;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSConfigBO#getConfigValue(java.lang.String)
     */
    @Override
    public SYSConfig getConfigValue(String ckey, String companyCode,
            String systemCode) {
        SYSConfig result = null;
        if (ckey != null) {
            SYSConfig condition = new SYSConfig();
            condition.setCkey(ckey);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            result = sysConfigDAO.select(condition);
            if (null == result) {
                throw new BizException("xn000000", "id记录不存在");
            }
        }
        return result;
    }

    @Override
    public List<SYSConfig> querySYSConfigList(String type) {
        SYSConfig condition = new SYSConfig();
        condition.setType(type);
        return sysConfigDAO.selectList(condition);
    }
}
