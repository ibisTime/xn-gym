package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.ICommentAO;
import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Comment;
import com.std.forum.dto.req.XN610143Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 提到我的评论分页查
 * @author: asus 
 * @since: 2017年3月30日 上午10:05:10 
 * @history:
 */
public class XN610143 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN610143Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPostAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commentAO.queryTDCommentPage(start, limit, condition,
            req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610143Req.class);
        StringValidater.validateBlank(req.getUserId());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
