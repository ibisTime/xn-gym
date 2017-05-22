package com.std.forum.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.forum.dao.ISplateDAO;
import com.std.forum.dao.base.support.AMybatisTemplate;
import com.std.forum.domain.Splate;
import com.std.forum.dto.res.XN610049Res;

@Repository("splateDAOImpl")
public class SplateDAOImpl extends AMybatisTemplate implements ISplateDAO {

    @Override
    public int insert(Splate data) {
        return super.insert(NAMESPACE.concat("insert_splate"), data);
    }

    @Override
    public int delete(Splate data) {
        return super.delete(NAMESPACE.concat("delete_splate"), data);
    }

    @Override
    public Splate select(Splate condition) {
        return super.select(NAMESPACE.concat("select_splate"), condition,
            Splate.class);
    }

    @Override
    public Long selectTotalCount(Splate condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_splate_count"),
            condition);
    }

    @Override
    public List<Splate> selectList(Splate condition) {
        return super.selectList(NAMESPACE.concat("select_splate"), condition,
            Splate.class);
    }

    @Override
    public List<Splate> selectList(Splate condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_splate"), start,
            count, condition, Splate.class);
    }

    @Override
    public int update(Splate data) {
        return super.update(NAMESPACE.concat("update_splate"), data);
    }

    @Override
    public int defaultSplate(Splate data) {
        return super.update(NAMESPACE.concat("update_default"), data);
    }

	@Override
	public List<XN610049Res> selectDetailSplate(Splate condition) {
		 return super.selectList(NAMESPACE.concat("select_detail_splate"), condition,
				 XN610049Res.class);
	}

}
