package com.std.gym.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.std.gym.ao.IKeywordAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.dto.req.XN622003Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 导入
 * @author: asus 
 * @since: 2017年7月19日 下午4:11:57 
 * @history:
 */
public class XN622003 extends AProcessor {
    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN622003Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.addKeyword(req.getReqList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622003Req.class);
        if (CollectionUtils.isEmpty(req.getReqList())) {
            throw new BizException("xn0000", "内容不能为空");
        }
    }

}
