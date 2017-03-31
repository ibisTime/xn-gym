package com.std.forum.api.impl;

import com.std.forum.ao.ISplateAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610043Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 新增小板块
 * @author: asus 
 * @since: 2017年3月20日 下午7:45:19 
 * @history:
 */
public class XN610043 extends AProcessor {
    private ISplateAO splateAO = SpringContextHolder.getBean(ISplateAO.class);

    private XN610043Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        splateAO.copySplate(req.getCompanyCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610043Req.class);
        StringValidater.validateBlank(req.getCompanyCode());
    }

}
