package com.std.forum.api.impl;

import com.std.forum.ao.IVideoAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610053Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 视频上架
 * @author: asus 
 * @since: 2017年3月28日 下午3:42:58 
 * @history:
 */
public class XN610053 extends AProcessor {
    private IVideoAO videoAO = SpringContextHolder.getBean(IVideoAO.class);

    private XN610053Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        videoAO.upVideo(req.getCode(),
            StringValidater.toInteger(req.getOrderNo()), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610053Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
        StringValidater.validateNumber(req.getOrderNo());
    }

}
