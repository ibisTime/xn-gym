package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.ISizeDataBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.ISizeDataDAO;
import com.std.gym.domain.SizeData;
import com.std.gym.exception.BizException;

@Component
public class SizeDataBOImpl extends PaginableBOImpl<SizeData> implements
        ISizeDataBO {

    @Autowired
    private ISizeDataDAO sizeDataDAO;

    @Override
    public void saveSizeData(SizeData data) {
        sizeDataDAO.insert(data);
    }

    @Override
    public int removeSizeData(String id) {
        int count = 0;
        if (StringUtils.isNotBlank(id)) {
            SizeData data = new SizeData();
            data.setId(id);
            count = sizeDataDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<SizeData> querySizeDataList(SizeData condition) {
        return sizeDataDAO.selectList(condition);
    }

    @Override
    public SizeData getSizeData(String code) {
        SizeData data = null;
        if (StringUtils.isNotBlank(code)) {
            SizeData condition = new SizeData();
            condition.setId(code);
            data = sizeDataDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
