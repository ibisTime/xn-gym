package com.std.forum.api.impl;

import com.std.forum.ao.IVideoAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610050Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 新增视频
 * @author: asus 
 * @since: 2017年3月28日 下午2:38:59 
 * @history:
 */
public class XN610050 extends AProcessor {
    private IVideoAO videoAO = SpringContextHolder.getBean(IVideoAO.class);

    private XN610050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return videoAO.addVideo(req.getName(),
            StringValidater.toInteger(req.getOrderNo()), req.getUpdater(),
            req.getRemark(), req.getCompanyCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610050Req.class);
        StringValidater.validateBlank(req.getName(), req.getUpdater(),
            req.getCompanyCode());
        StringValidater.validateNumber(req.getOrderNo());
    }
}
