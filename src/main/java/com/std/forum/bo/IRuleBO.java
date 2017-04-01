package com.std.forum.bo;

import com.std.forum.domain.Rule;
import com.std.forum.enums.ERuleKind;
import com.std.forum.enums.ERuleType;

public interface IRuleBO {
    public Long getJBTimesByUserId(String userId);

    public Rule getRuleByCondition(ERuleKind kind, ERuleType type, String level);
}
