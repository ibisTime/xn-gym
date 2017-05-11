package com.std.forum.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IBannerAO;
import com.std.forum.bo.IBannerBO;
import com.std.forum.bo.base.Page;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Banner;
import com.std.forum.enums.EBelong;
import com.std.forum.enums.EBoolean;
import com.std.forum.exception.BizException;

@Service
public class BannerAOImpl implements IBannerAO {

    @Autowired
    private IBannerBO bannerBO;

    @Override
    public void editBannerGlobal(String code, String name, String url,
            String pic, String location, String orderNo, String belong,
            String remark) {
        if (!EBelong.getBelongMap().containsKey(belong)) {
            throw new BizException("xn0000", "属于不允许自定义");
        }
        Banner banner = bannerBO.getBanner(code);
        if (!banner.getOrderNo().equals(orderNo)) {
            List<Banner> bannerList = bannerBO.queryBannerList(
                EBoolean.NO.getCode(), orderNo);
            if (CollectionUtils.isNotEmpty(bannerList)) {
                throw new BizException("xn0000", "顺序不能重复");
            }
        }
        if (EBelong.GLOBAL.getCode().equals(banner.getBelong())
                || EBelong.LOCAL.getCode().equals(banner.getBelong())) {
            bannerBO.refreshBannerByGlobal(code, name, url, pic, location,
                orderNo, belong, remark);
        } else {
            throw new BizException("xn0000", "地方性banner，不可修改");
        }
    }

    @Override
    public void editBannerLocal(String code, String name, String url,
            String pic, String location, String orderNo, String belong,
            String companyCode, String remark) {
        Banner banner = bannerBO.getBanner(code);
        if (!banner.getOrderNo().equals(orderNo)) {
            List<Banner> bannerList = bannerBO.queryBannerList(companyCode,
                orderNo);
            if (CollectionUtils.isNotEmpty(bannerList)) {
                throw new BizException("xn0000", "顺序不能重复");
            }
        }
        if (EBelong.LOCAL.getCode().equals(banner.getBelong())) {
            bannerBO.saveBanner(name, url, pic, location, orderNo, code,
                companyCode, remark);
        } else if (EBelong.GLOBAL.getCode().equals(banner.getBelong())) {
            throw new BizException("xn0000", "总部banner，地方不可修改");
        } else {
            bannerBO.refreshBannerByLocal(code, name, url, pic, location,
                orderNo, banner.getBelong(), companyCode, remark);
        }
    }

    @Override
    public Paginable<Banner> queryBannerPage(int start, int limit,
            Banner condition) {
        Paginable<Banner> page = null;
        List<String> companyCodeList = new ArrayList<String>();
        if (!condition.getCompanyCode().equals(EBoolean.NO.getCode())) {
            condition.setBelong("NO_1");
            companyCodeList.add(condition.getCompanyCode());
            companyCodeList.add("0");
            condition.setCompanyCodeList(companyCodeList);
            condition.setCompanyCode("");
            List<Banner> list = bannerBO.queryBannerList(condition);
            List<Banner> bannerList = new ArrayList<Banner>();
            bannerList.addAll(list);
            for (Banner banner : bannerList) {
                for (Banner ban : list) {
                    if (banner.getBelong().equals(ban.getCode())) {
                        list.remove(ban);
                        break;
                    }
                }
            }
            page = new Page<Banner>(start, limit, list.size());
            List<Banner> dataList = queryBanner(list, page.getStart(),
                page.getPageSize());
            // bannerBO.queryBannerList(condition,page.getStart(),
            // page.getPageSize());
            page.setList(dataList);
        } else {
            condition.setCompanyCode("");
            page = bannerBO.getPaginable(start, limit, condition);
        }
        return page;
    }

    private List<Banner> queryBanner(List<Banner> list, int start, int limit) {
        list.indexOf(start);
        list.lastIndexOf(limit);
        return list;
    }

    @Override
    public List<Banner> queryBannerList(String companyCode, String location) {
        List<Banner> bannerList = new ArrayList<Banner>();
        List<Banner> localList = bannerBO.queryBannerLocationList(companyCode,
            location);
        List<Banner> globalList = bannerBO.queryBannerLocationList(
            EBoolean.NO.getCode(), location);
        for (Banner global : globalList) {
            if (CollectionUtils.isNotEmpty(localList)) {
                for (Banner local : localList) {
                    // 本地菜单的上级是全局的
                    if (local.getBelong().equals(global.getCode())
                            && EBelong.GLOBAL.getCode().equalsIgnoreCase(
                                global.getBelong())) {
                        bannerList.add(global);
                    }
                    // 本地菜单的上级是可配的
                    if (local.getBelong().equals(global.getCode())
                            && EBelong.LOCAL.getCode().equalsIgnoreCase(
                                global.getBelong())) {
                        bannerList.add(local);
                    }
                }
            }
        }
        for (Banner banner : bannerList) {
            for (Banner global : globalList) {
                if (banner.getBelong().equals(global.getCode())
                        || banner.getCode().equals(global.getCode())) {
                    globalList.remove(global);
                    break;
                }
            }
        }
        bannerList.addAll(globalList);
        return bannerList;
    }

    @Override
    public Banner getBanner(String code) {
        return bannerBO.getBanner(code);
    }

}
