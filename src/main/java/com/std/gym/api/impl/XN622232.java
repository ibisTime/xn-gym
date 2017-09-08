package com.std.gym.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.gym.ao.IAttendAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.domain.Attend;
import com.std.gym.dto.req.XN622232Req;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 列表查询参与
 * @author: asus 
 * @since: 2017年9月7日 下午5:29:54 
 * @history:
 */
public class XN622232 extends AProcessor {
    private IAttendAO attendAO = SpringContextHolder.getBean(IAttendAO.class);

    private XN622232Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Attend condition = new Attend();
        condition.setCode(req.getCode());
        condition.setCoachCode(req.getCoachCode());
        condition.setCoachStatus(req.getCoachStatus());
        condition.setType(req.getType());
        condition.setRealName(req.getRealName());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IAttendAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return attendAO.queryAttendList(condition, req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622232Req.class);
    }

}
