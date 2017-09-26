package com.std.gym.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.gym.bo.ISmsOutBO;
import com.std.gym.dto.req.XN001200Req;
import com.std.gym.dto.req.XN804080Req;
import com.std.gym.dto.res.PKCodeRes;
import com.std.gym.enums.ESystemCode;
import com.std.gym.http.BizConnecter;
import com.std.gym.http.JsonUtils;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sentContent(String ownerId, String content) {
        try {
            XN001200Req req = new XN001200Req();
            req.setTokenId(ownerId);
            req.setUserId(ownerId);
            req.setContent(content);
            BizConnecter.getBizData("001200", JsonUtils.object2Json(req),
                Object.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常, 原因：" + e.getMessage());
        }
    }

    @Override
    public void sendSmsOut(String mobile, String content) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            req.setSystemCode(ESystemCode.SYSTEM_CODE.getCode());
            BizConnecter.getBizData("804080", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }

}
