package com.std.forum.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.forum.bo.IAccountBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Account;
import com.std.forum.dto.req.XN001303Req;
import com.std.forum.dto.req.XN002100Req;
import com.std.forum.dto.res.XN001303Res;
import com.std.forum.enums.EBizType;
import com.std.forum.enums.EChannelType;
import com.std.forum.exception.BizException;
import com.std.forum.http.BizConnecter;
import com.std.forum.http.JsonUtils;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午5:24:53 
 * @history:
 */
@Component
public class AccountBOImpl extends PaginableBOImpl<Account> implements
        IAccountBO {
    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            EChannelType currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote) {
        if (amount != null && amount != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setToUserId(toUserId);
            req.setCurrency(currency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public Long getAccountByUserId(String userId, EChannelType type) {
        Long amount = 0L;
        XN001303Req req = new XN001303Req();
        req.setUserId(userId);
        req.setCurrency(type.getCode());
        String jsonStr = BizConnecter.getBizData("002050",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN001303Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN001303Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "账户不存在");
        }
        XN001303Res res = list.get(0);
        amount = StringValidater.toLong(res.getAmount());
        return amount;
    }
}
