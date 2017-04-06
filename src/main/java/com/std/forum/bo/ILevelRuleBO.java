package com.std.forum.bo;

import java.util.List;

import com.std.forum.domain.LevelRule;
import com.std.forum.dto.res.XN805115Res;

public interface ILevelRuleBO {
    public LevelRule getLevelRule(String code);

    public List<XN805115Res> queryLevelRuleList();
}
