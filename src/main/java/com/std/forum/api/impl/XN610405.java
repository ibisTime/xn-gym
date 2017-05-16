package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.DateUtil;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.PageView;
import com.std.forum.dto.req.XN610400Req;
import com.std.forum.dto.req.XN610405Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 分页查询pv
 * @author William
 * @since  2017年5月15日 下午4:56:51
 * @history
 */
public class XN610405 extends AProcessor {
    private IPageViewAO pageViewAO = SpringContextHolder
        .getBean(IPageViewAO.class);

    private XN610405Req req = null;

    @Override
    public Object doBusiness() throws BizException {
    	PageView condition = new PageView();
    	condition.setCompanyCode(req.getCompanyCode());
    	condition.setViewDatetime(DateUtil.strToDate(
                req.getViewDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
    	condition.setStartDatetime(DateUtil.strToDate(
            req.getDateStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
    	condition.setEndDatetime(DateUtil.strToDate(
                req.getDateEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));
		String orderColumn = req.getOrderColumn();
	    if (StringUtils.isBlank(orderColumn)) {
	        orderColumn = IPageViewAO.DEFAULT_ORDER_COLUMN;
	    }
	    condition.setOrder(orderColumn, req.getOrderDir());
	   
                
	    int start = StringValidater.toInteger(req.getStart());
	    int limit = StringValidater.toInteger(req.getLimit());
	    return pageViewAO.queryPageViewPage(start, limit, condition);
        
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610405Req.class);
        StringValidater.validateBlank(req.getStart(),req.getLimit());
    }
}
