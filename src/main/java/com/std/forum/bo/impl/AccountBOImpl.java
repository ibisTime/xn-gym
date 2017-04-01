package com.std.forum.bo.impl;

import org.springframework.stereotype.Component;

import com.std.forum.bo.IAccountBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.domain.Account;
import com.std.forum.dto.req.XN002100Req;
import com.std.forum.enums.EBizType;
import com.std.forum.enums.EChannelType;
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

}
