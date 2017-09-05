package com.std.gym.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.std.gym.ao.IPerCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622127Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 填表
 * @author: asus 
 * @since: 2017年9月4日 下午9:10:26 
 * @history:
 */
public class XN622127 extends AProcessor {
    private IPerCourseOrderAO perCourseOrderAO = SpringContextHolder
        .getBean(IPerCourseOrderAO.class);

    private XN622127Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        perCourseOrderAO.toFullForm(req.getOrderCode(), req.getSizeDataList(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622127Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getUpdater());
        if (CollectionUtils.isEmpty(req.getSizeDataList())) {
            throw new BizException("xn0000", "您还没有填表格");
        }
    }

}
