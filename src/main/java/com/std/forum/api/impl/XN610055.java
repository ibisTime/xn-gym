package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IVideoAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Video;
import com.std.forum.dto.req.XN610055Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 分页查询视频
 * @author: asus 
 * @since: 2017年3月28日 下午3:23:43 
 * @history:
 */
public class XN610055 extends AProcessor {
    private IVideoAO videoAO = SpringContextHolder.getBean(IVideoAO.class);

    private XN610055Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Video condition = new Video();
        condition.setName(req.getName());
        condition.setStatus(req.getStatus());
        condition.setCompanyCode(req.getCompanyCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IVideoAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return videoAO.queryVideoPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610055Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
