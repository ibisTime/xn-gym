package com.std.forum.bo.impl;

import java.util.Date;
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
    public void savePageView(String companyCode) {
        PageView data = new PageView();
        String code = OrderNoGenerater.generateME(EPrefixCode.PAGEVIEW
            .getCode());
        data.setCode(code);
        data.setCompanyCode(companyCode);
        data.setPageViewNum(1);
        data.setViewDatetime(new Date());
        pageViewDAO.insert(data);
    }

    @Override
    public int refreshPageView(PageView data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setPageViewNum(data.getPageViewNum() + 1);
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
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<PageView> queryPageViewList(String companyCode, Date datetime) {
        PageView condition = new PageView();
        condition.setViewDatetime(datetime);
        condition.setCompanyCode(companyCode);
        return pageViewDAO.selectList(condition);
    }
}
