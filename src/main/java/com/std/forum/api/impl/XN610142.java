package com.std.forum.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.forum.ao.IPostAO;
import com.std.forum.ao.IPostTalkAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.PostTalk;
import com.std.forum.dto.req.XN610142Req;
import com.std.forum.enums.ETalkType;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 打赏列表查
 * @author: asus 
 * @since: 2017年3月27日 下午1:52:37 
 * @history:
 */
public class XN610142 extends AProcessor {
    private IPostTalkAO postTalkAO = SpringContextHolder
        .getBean(IPostTalkAO.class);

    private XN610142Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PostTalk condition = new PostTalk();
        condition.setPostCode(req.getPostCode());
        condition.setType(ETalkType.DS.getCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IPostAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return postTalkAO.queryPostTalkPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610142Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
