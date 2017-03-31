package com.std.forum.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.ISplateAO;
import com.std.forum.bo.IBplateBO;
import com.std.forum.bo.IBplateTemplateBO;
import com.std.forum.bo.IPostBO;
import com.std.forum.bo.ISplateBO;
import com.std.forum.bo.ISplateTemplateBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.common.DateUtil;
import com.std.forum.domain.BplateTemplate;
import com.std.forum.domain.Post;
import com.std.forum.domain.Splate;
import com.std.forum.domain.SplateTemplate;
import com.std.forum.dto.req.XN610040Req;
import com.std.forum.dto.req.XN610042Req;
import com.std.forum.dto.res.XN610046Res;
import com.std.forum.enums.ELocation;
import com.std.forum.enums.EPlateStatus;
import com.std.forum.exception.BizException;

@Service
public class SplateAOImpl implements ISplateAO {

    @Autowired
    private ISplateBO splateBO;

    @Autowired
    private IBplateBO bplateBO;

    @Autowired
    private ISplateTemplateBO splateTemplateBO;

    @Autowired
    private IBplateTemplateBO bplateTemplateBO;

    @Autowired
    private IPostBO postBO;

    @Override
    public String addSplate(XN610040Req req) {
        return splateBO.saveSplate(req.getName(), req.getParentCode(),
            req.getPic(), req.getOrderNo(), req.getUserId(),
            req.getCompanyCode(), req.getStatus(), req.getUpdater(),
            req.getRemark());
    }

    @Override
    public int editSplate(XN610042Req req) {
        if (!splateBO.isSplateExist(req.getCode())) {
            throw new BizException("xn0000", "小模板编号不存在");
        }
        return splateBO.refreshSplate(req.getCode(), req.getName(),
            req.getParentCode(), req.getPic(), req.getOrderNo(),
            req.getUserId(), req.getCompanyCode(), req.getStatus(),
            req.getUpdater(), req.getRemark());
    }

    @Override
    public int dropSplate(String code) {
        Splate splate = splateBO.getSplate(code);
        if (EPlateStatus.ENABLE.getCode().equals(splate.getStatus())) {
            throw new BizException("xn0000", "小板块正在被使用，不可以删除");
        }
        return splateBO.removeSplate(code);
    }

    @Override
    public Paginable<Splate> querySplatePage(int start, int limit,
            Splate condition) {
        return splateBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Splate> querySplateList(Splate condition) {
        return splateBO.querySplateList(condition);
    }

    @Override
    public XN610046Res getSplate(String code) {
        XN610046Res res = new XN610046Res();
        Splate splate = splateBO.getSplate(code);
        Long allPostCount = postBO.getPostNum(splate.getCode());
        Post condition = new Post();
        condition.setPublishDatetimeStart(DateUtil.getTodayStart());
        condition.setPublishDatetimeEnd(DateUtil.getTodayEnd());
        Long todayPostCount = postBO.getPostNum(condition);
        Long top = postBO.getPostLocation(ELocation.JH.getCode());
        Long essence = postBO.getPostLocation(ELocation.ZD.getCode());
        res.setSplate(splate);
        res.setAllPostCount(allPostCount);
        res.setTodayPostCount(todayPostCount);
        res.setTop(top);
        res.setEssence(essence);
        return res;
    }

    @Override
    public void copySplate(String companyCode) {
        List<SplateTemplate> splateTemplateList = splateTemplateBO
            .querySplateTemplateList();
        List<BplateTemplate> bplateTemplateList = bplateTemplateBO
            .queryBplateTemplateList();
        for (BplateTemplate bplateTemplate : bplateTemplateList) {
            bplateBO.saveBplate(bplateTemplate.getName(),
                EPlateStatus.ENABLE.getCode(), bplateTemplate.getOrderNo(),
                companyCode, bplateTemplate.getUpdater());
        }
        for (SplateTemplate splateTemplate : splateTemplateList) {
            splateBO.saveSplate(splateTemplate.getName(),
                splateTemplate.getBplateCode(), splateTemplate.getPic(),
                splateTemplate.getOrderNo(), null, companyCode,
                EPlateStatus.ENABLE.getCode(), splateTemplate.getUpdater(),
                splateTemplate.getRemark());
        }
    }
}
