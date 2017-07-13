package com.std.gym.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IPraiseItemBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.dao.IPraiseItemDAO;
import com.std.gym.domain.PraiseItem;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.exception.BizException;

@Component
public class PraiseItemBOImpl extends PaginableBOImpl<PraiseItem> implements
        IPraiseItemBO {

    @Autowired
    private IPraiseItemDAO praiseItemDAO;

    @Override
    public boolean isPraiseItemExist(String code) {
        PraiseItem condition = new PraiseItem();
        condition.setCode(code);
        if (praiseItemDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String savePraiseItem(PraiseItem data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EPrefixCode.PRAISEITEM.getCode());
            data.setCode(code);
            praiseItemDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removePraiseItem(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            PraiseItem data = new PraiseItem();
            data.setCode(code);
            count = praiseItemDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPraiseItem(PraiseItem data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = praiseItemDAO.update(data);
        }
        return count;
    }

    @Override
    public List<PraiseItem> queryPraiseItemList(PraiseItem condition) {
        return praiseItemDAO.selectList(condition);
    }

    @Override
    public PraiseItem getPraiseItem(String code) {
        PraiseItem data = null;
        if (StringUtils.isNotBlank(code)) {
            PraiseItem condition = new PraiseItem();
            condition.setCode(code);
            data = praiseItemDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
