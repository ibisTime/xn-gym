package com.std.forum.api.impl;

import com.std.forum.ao.IVideoAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610051Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 删除视频
 * @author: asus 
 * @since: 2017年3月28日 下午2:57:24 
 * @history:
 */
public class XN610051 extends AProcessor {
    private IVideoAO videoAO = SpringContextHolder.getBean(IVideoAO.class);

    private XN610051Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return videoAO.dropVideo(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610051Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
