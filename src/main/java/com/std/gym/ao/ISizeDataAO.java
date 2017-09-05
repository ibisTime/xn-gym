package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.SizeData;

@Component
public interface ISizeDataAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void addSizeData(SizeData data);

    public int dropSizeData(String code);

    public int editSizeData(SizeData data);

    public Paginable<SizeData> querySizeDataPage(int start, int limit,
            SizeData condition);

    public List<SizeData> querySizeDataList(SizeData condition);

    public SizeData getSizeData(String code);

}
