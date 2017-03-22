package com.std.forum.api.impl;

import com.std.forum.ao.IBplateTemplateAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.dto.req.XN610017Req;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 列表查询大板块模板
 * @author: asus 
 * @since: 2017年3月22日 下午2:08:37 
 * @history:
 */
public class XN610017 extends AProcessor {
    private IBplateTemplateAO bplateTemplateAO = SpringContextHolder
        .getBean(IBplateTemplateAO.class);

    private XN610017Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bplateTemplateAO.queryBplateTemplateList(req.getName());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610017Req.class);
    }

}
