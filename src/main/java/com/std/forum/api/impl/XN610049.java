package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.ISplateAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.domain.Splate;
import com.std.forum.dto.req.XN610048Req;
import com.std.forum.dto.req.XN610049Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 列表查询小版块(PC专用)
 * @author William
 * @since  2017年5月19日 上午11:48:57
 * @history
 */
public class XN610049 extends AProcessor {
    private ISplateAO splateAO = SpringContextHolder.getBean(ISplateAO.class);

    private XN610049Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Splate condition = new Splate();
        condition.setName(req.getName());
        condition.setBplateCode(req.getParentCode());
        condition.setStatus(req.getStatus());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setModerator(req.getUserId());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISplateAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setStatusList(req.getStatusList());
        condition.setOrder(orderColumn, req.getOrderDir());
        return splateAO.querySplateDetailList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610049Req.class);
    }
}
