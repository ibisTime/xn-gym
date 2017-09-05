package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ISizeDataAO;
import com.std.gym.bo.ISizeDataBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.SizeData;

@Service
public class SizeDataAOImpl implements ISizeDataAO {

    @Autowired
    private ISizeDataBO sizeDataBO;

    @Override
    public void addSizeData(SizeData data) {
        sizeDataBO.saveSizeData(data);
    }

    @Override
    public int editSizeData(SizeData data) {
        return 0;
    }

    @Override
    public int dropSizeData(String code) {
        return sizeDataBO.removeSizeData(code);
    }

    @Override
    public Paginable<SizeData> querySizeDataPage(int start, int limit,
            SizeData condition) {
        return sizeDataBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SizeData> querySizeDataList(SizeData condition) {
        return sizeDataBO.querySizeDataList(condition);
    }

    @Override
    public SizeData getSizeData(String code) {
        return sizeDataBO.getSizeData(code);
    }
}
