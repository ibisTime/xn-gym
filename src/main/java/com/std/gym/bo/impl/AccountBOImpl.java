package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.common.JsonUtil;
import com.std.gym.common.PropertiesUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Account;
import com.std.gym.dto.req.XN001303Req;
import com.std.gym.dto.req.XN002100Req;
import com.std.gym.dto.req.XN002501Req;
import com.std.gym.dto.res.XN001303Res;
import com.std.gym.dto.res.XN002501Res;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EChannelType;
import com.std.gym.exception.BizException;
import com.std.gym.http.BizConnecter;
import com.std.gym.http.JsonUtils;

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
        amount = StringValidater.toLong(res.getAddAmount());
        return amount;
    }

    @Override
    public XN002501Res doWeiXinH5PayRemote(String fromUserId,
            String fromOpenId, String toUserId, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String payGroup) {
        // 获取微信H5支付信息
        XN002501Req req = new XN002501Req();
        req.setFromUserId(fromUserId);
        req.setFromOpenId(fromOpenId);
        req.setToUserId(toUserId);
        req.setTransAmount(String.valueOf(amount));
        req.setBizType(bizType.getCode());
        req.setFromBizNote(fromBizNote);
        req.setToBizNote(toBizNote);
        req.setPayGroup(payGroup);
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        System.out.println(fromUserId + "" + fromOpenId + "" + toUserId + ""
                + amount + "" + bizType + "" + fromBizNote + "" + toBizNote
                + "" + payGroup);
        XN002501Res res = BizConnecter.getBizData("002501",
            JsonUtil.Object2Json(req), XN002501Res.class);
        return res;
    }
}
