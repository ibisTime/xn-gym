package com.std.gym.bo;

import java.util.List;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.SizeData;

public interface ISizeDataBO extends IPaginableBO<SizeData> {

    public void saveSizeData(SizeData data);

    public int removeSizeData(String code);

    public List<SizeData> querySizeDataList(String orderCode);

    public SizeData getSizeData(String code);

}
