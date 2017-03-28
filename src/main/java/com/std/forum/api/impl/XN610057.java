package com.std.forum.api.impl;

import com.std.forum.ao.IVideoAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.domain.Video;
import com.std.forum.dto.req.XN610057Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 列表查询视频
 * @author: asus 
 * @since: 2017年3月28日 下午3:32:56 
 * @history:
 */
public class XN610057 extends AProcessor {
    private IVideoAO videoAO = SpringContextHolder.getBean(IVideoAO.class);

    private XN610057Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Video condition = new Video();
        condition.setName(req.getName());
        condition.setStatus(req.getStatus());
        condition.setCompanyCode(req.getCompanyCode());
        return videoAO.queryVideoList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610057Req.class);
    }
}
