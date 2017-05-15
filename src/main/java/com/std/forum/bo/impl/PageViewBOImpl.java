package com.std.forum.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.IPageViewBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.OrderNoGenerater;
import com.std.forum.dao.IPageViewDAO;
import com.std.forum.domain.PageView;
import com.std.forum.enums.EPrefixCode;
import com.std.forum.exception.BizException;

@Component
public class PageViewBOImpl extends PaginableBOImpl<PageView> implements
        IPageViewBO {

    @Autowired
    private IPageViewDAO pageViewDAO;

    @Override
    public boolean isPageViewExist(String code) {
        PageView condition = new PageView();
        condition.setCode(code);
        if (pageViewDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String savePageView(PageView data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateME(EPrefixCode.PAGEVIEW.getCode());
            data.setCode(code);
            pageViewDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removePageView(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            PageView data = new PageView();
            data.setCode(code);
            count = pageViewDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPageView(PageView data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = pageViewDAO.update(data);
        }
        return count;
    }

    @Override
    public List<PageView> queryPageViewList(PageView condition) {
        return pageViewDAO.selectList(condition);
    }

    @Override
    public PageView getPageView(String code) {
        PageView data = null;
        if (StringUtils.isNotBlank(code)) {
            PageView condition = new PageView();
            condition.setCode(code);
            data = pageViewDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
