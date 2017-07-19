package com.std.gym.api.impl;

import com.std.gym.ao.ISYSDictAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN807700Req;
import com.std.gym.dto.res.PKIdRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 新增数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:45:23 
 * @history:
 */
public class XN807700 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN807700Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return new PKIdRes(sysDictAO.addSYSDict(req.getType(),
            req.getParentKey(), req.getDkey(), req.getDvalue(),
            req.getUpdater(), req.getRemark(), req.getSystemCode()));
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN807700Req.class);
        StringValidater.validateBlank(req.getType(), req.getDkey(),
            req.getDvalue(), req.getUpdater(), req.getSystemCode());
    }
}
