package com.std.forum.bo.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.forum.bo.ICompanyBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.domain.Company;
import com.std.forum.dto.req.XN001450Req;
import com.std.forum.dto.req.XN806013Req;
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

    @Override
    public List<Company> queryCompanyList() {
        XN806013Req req = new XN806013Req();
        req.setSystemCode("CD-CCSW000008");
        String jsonStr = BizConnecter.getBizData("806013",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<Company> list = gson.fromJson(jsonStr,
            new TypeToken<List<Company>>() {
            }.getType());
        return list;
    }
}
