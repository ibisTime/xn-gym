package com.std.gym.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.gym.dao.ISizeDataDAO;
import com.std.gym.dao.base.support.AMybatisTemplate;
import com.std.gym.domain.SizeData;

@Repository("sizeDataDAOImpl")
public class SizeDataDAOImpl extends AMybatisTemplate implements ISizeDataDAO {

    @Override
    public int insert(SizeData data) {
        return super.insert(NAMESPACE.concat("insert_sizeData"), data);
    }

    @Override
    public int delete(SizeData data) {
        return super.delete(NAMESPACE.concat("delete_sizeData"), data);
    }

    @Override
    public SizeData select(SizeData condition) {
        return super.select(NAMESPACE.concat("select_sizeData"), condition,
            SizeData.class);
    }

    @Override
    public Long selectTotalCount(SizeData condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_sizeData_count"), condition);
    }

    @Override
    public List<SizeData> selectList(SizeData condition) {
        return super.selectList(NAMESPACE.concat("select_sizeData"), condition,
            SizeData.class);
    }

    @Override
    public List<SizeData> selectList(SizeData condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sizeData"), start,
            count, condition, SizeData.class);
    }

}
