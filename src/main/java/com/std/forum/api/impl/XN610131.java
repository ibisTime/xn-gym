package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.DateUtil;
import com.std.forum.common.JsonUtil;
import com.std.forum.domain.Post;
import com.std.forum.dto.req.XN610131Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/** 
 * 列表查询帖子
 * @author: zuixian 
 * @since: 2016年9月28日 下午1:52:13 
 * @history:
 */
public class XN610131 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN610131Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Post condition = new Post();
        condition.setUserId(req.getUserId());
        condition.setTitle(req.getTitle());
        condition.setKeyword(req.getKeyword());
        condition.setPublisher(req.getPublisher());
        condition.setStatus(req.getStatus());
        condition.setIsLock(req.getIsLock());
        condition.setLocation(req.getLocation());
        condition.setPlateCode(req.getPlateCode());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setPublishDatetimeStart(DateUtil.strToDate(
            req.getDateStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setPublishDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        return postAO.queryPostList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610131Req.class);
    }
}
