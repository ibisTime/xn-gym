package com.std.forum.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.forum.bo.ILevelRuleBO;
import com.std.forum.domain.LevelRule;
import com.std.forum.dto.req.XN805114Req;
import com.std.forum.dto.req.XN805115Req;
import com.std.forum.dto.res.XN805115Res;
import com.std.forum.exception.BizException;
import com.std.forum.http.BizConnecter;
import com.std.forum.http.JsonUtils;

@Component
public class LevelRuleBOImpl implements ILevelRuleBO {

    @Override
    public LevelRule getLevelRule(String code) {
        LevelRule data = null;
        if (StringUtils.isNotBlank(code)) {
            XN805114Req req = new XN805114Req();
            req.setCode(code);
            String jsonStr = BizConnecter.getBizData("805114",
                JsonUtils.object2Json(req));
            Gson gson = new Gson();
            data = gson.fromJson(jsonStr, new TypeToken<LevelRule>() {
            }.getType());
        }
        return data;
    }

    @Override
    public List<XN805115Res> queryLevelRuleList() {
        List<XN805115Res> levelRuleList = null;
        XN805115Req req = new XN805115Req();
        req.setSystemCode("CD-CCSW000008");
        String jsonStr = BizConnecter.getBizData("805115",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        levelRuleList = gson.fromJson(jsonStr,
            new TypeToken<List<XN805115Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(levelRuleList)) {
            throw new BizException("xn0000", "等级数据为空");
        }
        return levelRuleList;
    }
}
