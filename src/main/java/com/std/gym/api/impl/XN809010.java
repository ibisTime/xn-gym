package com.std.gym.api.impl;

import com.std.gym.ao.ISYSConfigAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.SYSConfig;
import com.std.gym.dto.req.XN809010Req;
import com.std.gym.dto.res.PKIdRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 新增系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:51:37 
 * @history:
 */
public class XN809010 extends AProcessor {

    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN809010Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSConfig data = new SYSConfig();
        data.setToSystem("8");// 8 代表生意家 作为服务时启用该字段
        data.setCkey(req.getCkey());
        data.setCvalue(req.getCvalue());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        return new PKIdRes(sysConfigAO.addSYSConfig(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN809010Req.class);
        StringValidater.validateBlank(req.getCkey(), req.getCvalue(),
            req.getNote(), req.getUpdater());

    }

}
