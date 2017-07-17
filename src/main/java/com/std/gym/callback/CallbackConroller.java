package com.std.gym.callback;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.std.gym.ao.IActivityOrderAO;
import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EPayType;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class CallbackConroller {

    private static Logger logger = Logger.getLogger(CallbackConroller.class);

    @Autowired
    IActivityOrderAO activityOrderAO;

    @Autowired
    IOrgCourseOrderAO orgCourseOrderAO;

    @RequestMapping("/thirdPay/callback")
    public synchronized void doCallbackZhpay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        boolean isSuccess = Boolean.valueOf(request.getParameter("isSuccess"));
        String payGroup = request.getParameter("payGroup");
        String payCode = request.getParameter("payCode");
        Long amount = Long.valueOf(request.getParameter("transAmount"));
        String bizType = request.getParameter("bizType");
        // 支付成功，商户处理后同步返回给微信参数
        if (!isSuccess) {
            logger.info("****业务类型<" + bizType + "> payGroup <" + payGroup
                    + "> payCode <" + payCode + ">回调失败****");
        } else {
            try {
                if (EBizType.AJ_HDGM.getCode().equals(bizType)) {
                    logger.info("**** 自玩自健活动购买支付回调 payGroup <" + payGroup
                            + "> payCode <" + payCode + ">start****");
                    activityOrderAO.paySuccess(payGroup, payCode, amount,
                        EPayType.WEIXIN.getCode());
                    logger.info("**** 自玩自健活动购买支付回调 payGroup <" + payGroup
                            + "> payCode <" + payCode + ">end****");
                } else if (EBizType.AJ_TKGM.getCode().equals(bizType)) {
                    logger.info("**** 自玩自健团课购买支付回调 payGroup <" + payGroup
                            + "> payCode <" + payCode + ">start****");
                    orgCourseOrderAO.paySuccess(payGroup, payCode, amount,
                        EPayType.WEIXIN.getCode());
                    logger.info("**** 自玩自健团课购买支付回调 payGroup <" + payGroup
                            + "> payCode <" + payCode + ">end****");
                }
            } catch (Exception e) {
                logger.error("支付回调异常payGroup <" + payGroup + "> payCode <"
                        + payCode + ">异常如下：" + e.getMessage());
            }
        }
    }
}
