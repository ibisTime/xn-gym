package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.ISplateAO;
import com.std.forum.ao.ISplateTemplateAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Splate;
import com.std.forum.dto.req.XN610098Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 前端小版块显示
 * @author William
 * @since  2017年5月10日 下午5:32:16
 * @history
 */
public class XN610098 extends AProcessor {

    private ISplateAO splateAO = SpringContextHolder.getBean(ISplateAO.class);

    private XN610098Req req;

    @Override
    public Object doBusiness() throws BizException {
        Splate condition = new Splate();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISplateTemplateAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return splateAO.querySmallSplateList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610098Req.class);
        StringValidater.validateBlank(req.getCompanyCode(), req.getStatus());
    }

}
