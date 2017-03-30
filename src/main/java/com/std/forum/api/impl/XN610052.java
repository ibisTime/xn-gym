package com.std.forum.api.impl;

import com.std.forum.ao.IVideoAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610052Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 修改视频
 * @author: asus 
 * @since: 2017年3月28日 下午2:38:59 
 * @history:
 */
public class XN610052 extends AProcessor {
    private IVideoAO videoAO = SpringContextHolder.getBean(IVideoAO.class);

    private XN610052Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        videoAO.editVideo(req.getCode(), req.getName(), req.getPic(),
            req.getUrl(), StringValidater.toInteger(req.getOrderNo()),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610052Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getPic(), req.getUrl(), req.getUpdater());
        StringValidater.validateNumber(req.getOrderNo());
    }
}
