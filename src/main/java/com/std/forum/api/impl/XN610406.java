package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.DateUtil;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.PageView;
import com.std.forum.dto.req.XN610406Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 列表查询pv
 * @author William
 * @since  2017年5月15日 下午4:56:51
 * @history
 */
public class XN610406 extends AProcessor {
    private IPageViewAO pageViewAO = SpringContextHolder
        .getBean(IPageViewAO.class);

    private XN610406Req req = null;

    @Override
    public Object doBusiness() throws BizException {
    	PageView condition = new PageView();
    	condition.setCompanyCode(req.getCompanyCode());
    	condition.setStartDatetime(DateUtil.strToDate(
            req.getDateStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
    	condition.setEndDatetime(DateUtil.strToDate(
                req.getDateEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));    	
	    return pageViewAO.queryPageViewList(condition);
        
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610406Req.class);
        
    }
}
