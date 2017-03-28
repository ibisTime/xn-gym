package com.std.forum.bo.impl;

import org.springframework.stereotype.Component;

import com.std.forum.bo.ICompanyBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.domain.Company;
import com.std.forum.dto.req.XN001450Req;
import com.std.forum.dto.res.XN001450Res;
import com.std.forum.exception.BizException;
import com.std.forum.http.BizConnecter;
import com.std.forum.http.JsonUtils;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {

    @Override
    public XN001450Res getCompany(String companyCode) {
        XN001450Req req = new XN001450Req();
        req.setTokenId(companyCode);
        req.setCompanyCode(companyCode);
        XN001450Res res = BizConnecter.getBizData("001450",
            JsonUtils.object2Json(req), XN001450Res.class);
        if (res == null) {
            throw new BizException("XN000000", "公司不存在");
        }
        return res;
    }
}
