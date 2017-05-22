package com.std.forum.dao;

import java.util.List;

import com.std.forum.dao.base.IBaseDAO;
import com.std.forum.domain.Splate;
import com.std.forum.dto.res.XN610049Res;

//dao层 
public interface ISplateDAO extends IBaseDAO<Splate> {
    String NAMESPACE = ISplateDAO.class.getName().concat(".");

    public int update(Splate data);

    public int defaultSplate(Splate data);
    
    public List<XN610049Res> selectDetailSplate(Splate condition);
}
