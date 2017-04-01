package com.std.forum.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.forum.bo.IRuleBO;
import com.std.forum.domain.Rule;
import com.std.forum.dto.req.XN807726Req;
import com.std.forum.dto.res.XN807726Res;
import com.std.forum.enums.ERuleKind;
import com.std.forum.enums.ERuleType;
import com.std.forum.exception.BizException;
import com.std.forum.http.BizConnecter;
import com.std.forum.http.JsonUtils;

@Component
public class RuleBOImpl implements IRuleBO {

    @Override
    public Rule getRuleByCondition(ERuleKind kind, ERuleType type, String level) {
        XN807726Req req = new XN807726Req();
        req.setKind(kind.getCode());
        req.setType(type.getCode());
        req.setLevel(level);
        req.setSystemCode("CD-CCSW000008");
        String jsonStr = BizConnecter.getBizData("807726",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN807726Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN807726Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "积分规则不存在");
        }
        XN807726Res res = list.get(0);
        Rule rule = new Rule();
        rule.setValue(res.getValue());
        return rule;
    }

    @Override
    public Long getJBTimesByUserId(String userId) {
        Long times = 0L;
        String jsonStr = BizConnecter.getBizData("807728",
            JsonUtils.string2Json("userId", userId));
        Gson gson = new Gson();
        Rule rule = gson.fromJson(jsonStr, new TypeToken<Rule>() {
        }.getType());
        if (rule != null) {
            times = Long.valueOf(rule.getValue());
        }
        return times;
    }
}
