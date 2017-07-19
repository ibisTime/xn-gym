package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.ICommentAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Comment;
import com.std.gym.dto.req.XN622145Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 分页查询评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:38:09 
 * @history:
 */
public class XN622145 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN622145Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        condition.setCoachCode(req.getCoachCode());
        condition.setProductCode(req.getProductCode());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICommentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commentAO.queryCommentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622145Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
