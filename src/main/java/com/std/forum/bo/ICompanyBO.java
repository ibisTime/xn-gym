package com.std.forum.bo;

import java.util.List;

import com.std.forum.domain.Company;
import com.std.forum.dto.res.XN001450Res;

public interface ICompanyBO {

    public XN001450Res getCompany(String companyCode);

    public List<Company> queryCompanyList();
}
