package com.std.gym.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.gym.bo.base.Paginable;
import com.std.gym.domain.PraiseItem;

@Component
public interface IPraiseItemAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPraiseItem(PraiseItem data);

    public int dropPraiseItem(String code);

    public int editPraiseItem(PraiseItem data);

    public Paginable<PraiseItem> queryPraiseItemPage(int start, int limit,
            PraiseItem condition);

    public List<PraiseItem> queryPraiseItemList(PraiseItem condition);

    public PraiseItem getPraiseItem(String code);

}
