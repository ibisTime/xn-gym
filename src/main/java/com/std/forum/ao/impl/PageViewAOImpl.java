package com.std.forum.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IPageViewAO;
import com.std.forum.bo.ICompanyBO;
import com.std.forum.bo.IPageViewBO;
import com.std.forum.bo.IPostBO;
import com.std.forum.bo.IUserBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.common.DateUtil;
import com.std.forum.domain.PageView;
import com.std.forum.domain.Post;
import com.std.forum.domain.User;
import com.std.forum.dto.req.XN610408Req;
import com.std.forum.dto.res.XN610408Res;
import com.std.forum.exception.BizException;

@Service
public class PageViewAOImpl implements IPageViewAO {

    @Autowired
    private IPageViewBO pageViewBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IPostBO postBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public void addPageView(String companyCode) {
        List<PageView> pageViewList = pageViewBO.queryPageViewList(companyCode,
            DateUtil.strToDate(
                DateUtil.getToday(DateUtil.FRONT_DATE_FORMAT_STRING),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        if (CollectionUtils.isEmpty(pageViewList)) {
            pageViewBO.savePageView(companyCode);
        } else {
            PageView pageView = pageViewList.get(0);
            pageViewBO.refreshPageView(pageView);
        }
    }

    @Override
    public void editPageView(PageView data) {
        if (!pageViewBO.isPageViewExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        pageViewBO.refreshPageView(data);
    }

    @Override
    public Paginable<PageView> queryPageViewPage(int start, int limit,
            PageView condition) {
        return pageViewBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PageView> queryPageViewList(PageView condition) {
        return pageViewBO.queryPageViewList(condition);
    }

    @Override
    public PageView getPageView(String code) {
        return pageViewBO.getPageView(code);
    }

    // 查询统计情况 帖子总数 用户总数 PV数
    @Override
    public XN610408Res queryNum(String companyCode,String dateStartReq,String dateEndReq){
    	XN610408Res res = new XN610408Res();
    	if(StringUtils.isBlank(companyCode)){
    		return res ;
    	}
    	Date dateStart = DateUtil.strToDate(
    			dateStartReq, DateUtil.FRONT_DATE_FORMAT_STRING);
    	Date dateEnd =DateUtil.getEndDatetime(dateEndReq);
    	
    	//查询城市信息
    	String companyName = companyBO.getCompany(companyCode).getName();
    	//查询帖子总数
    	Post postCondition = new Post();
    	postCondition.setCompanyCode(companyCode);
    	postCondition.setPublishDatetimeStart(dateStart);
    	postCondition.setPublishDatetimeEnd(dateEnd);
    	long postNum = postBO.getPostNum(postCondition);
    	//查询PV
    	PageView pageViewCondition = new PageView();
    	pageViewCondition.setCompanyCode(companyCode);
    	pageViewCondition.setStartDatetime(dateStart);
    	pageViewCondition.setEndDatetime(dateEnd);
    	PageView pageView = pageViewBO.getPageViewNum(pageViewCondition);
    	Long pvNum ;    	    	
    	if(pageView==null){
    		pvNum = 0L ;
    	}else{
    		pvNum = pageView.getPageViewNum();
    	}
    	//查询用户总人数
    	User userCondition = new User();
    	userCondition.setCompanyCode(companyCode);
    	userCondition.setDateStart(dateStart);
    	userCondition.setDateEnd(dateEnd);
    	Long userNum = userBO.getUserTotal(userCondition);  
    	//填充数据
    	res.setCompanyCode(companyCode);
    	res.setCompanyName(companyName);
    	res.setPageViewNum(pvNum);
    	res.setPostNum(postNum);
    	res.setUserNum(userNum);
    	return res ;
    	}
}
