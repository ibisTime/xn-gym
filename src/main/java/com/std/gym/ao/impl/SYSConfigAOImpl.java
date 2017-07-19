package com.std.gym.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ISYSConfigAO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.SYSConfig;
import com.std.gym.exception.BizException;

/**
 * 系统参数
 * @author: xieyj 
 * @since: 2016年7月23日 下午3:50:20 
 * @history:
 */
@Service
public class SYSConfigAOImpl implements ISYSConfigAO {

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public int editSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            count = sysConfigBO.refreshSYSConfig(data);
        } else {
            throw new BizException("lh5031", "系统参数ID不存在！");
        }
        return count;
    }

    @Override
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition) {
        return sysConfigBO.getPaginable(start, limit, condition);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        return sysConfigBO.getConfig(id);
    }

    @Override
    public SYSConfig getSYSConfig(String key, String companyCode,
            String systemCode) {
        return sysConfigBO.getConfigValue(key, companyCode, systemCode);
    }
}
