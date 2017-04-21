package com.std.forum.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.ISplateAO;
import com.std.forum.bo.IBplateBO;
import com.std.forum.bo.IBplateTemplateBO;
import com.std.forum.bo.IPostBO;
import com.std.forum.bo.IPostTalkBO;
import com.std.forum.bo.ISplateBO;
import com.std.forum.bo.ISplateTemplateBO;
import com.std.forum.bo.IUserBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.common.DateUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Bplate;
import com.std.forum.domain.BplateTemplate;
import com.std.forum.domain.Post;
import com.std.forum.domain.PostTalk;
import com.std.forum.domain.Splate;
import com.std.forum.domain.SplateTemplate;
import com.std.forum.domain.User;
import com.std.forum.dto.req.XN610040Req;
import com.std.forum.dto.req.XN610042Req;
import com.std.forum.dto.res.XN610046Res;
import com.std.forum.enums.EBoolean;
import com.std.forum.enums.ELocation;
import com.std.forum.enums.EPlateStatus;
import com.std.forum.enums.EPostStatus;
import com.std.forum.enums.ETalkType;
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
    private IUserBO userBO;

    @Autowired
    private IPostBO postBO;

    @Autowired
    private IPostTalkBO postTalkBO;

    @Override
    public String addSplate(XN610040Req req) {
        this.checkIsDefault(req.getIsDefault(), req.getCompanyCode());
        return splateBO.saveSplate(req.getName(), req.getParentCode(),
            req.getPic(), req.getOrderNo(), req.getUserId(),
            StringValidater.toInteger(req.getIsDefault()),
            req.getCompanyCode(), req.getStatus(), req.getUpdater(),
            req.getRemark());
    }

    @Override
    public int editSplate(XN610042Req req) {
        if (!splateBO.isSplateExist(req.getCode())) {
            throw new BizException("xn0000", "小模板编号不存在");
        }
        this.checkIsDefault(req.getIsDefault(), req.getCompanyCode());
        return splateBO.refreshSplate(req.getCode(), req.getName(),
            req.getParentCode(), req.getPic(), req.getOrderNo(),
            req.getUserId(), StringValidater.toInteger(req.getIsDefault()),
            req.getCompanyCode(), req.getStatus(), req.getUpdater(),
            req.getRemark());
    }

    private void checkIsDefault(String isDefault, String companyCode) {
        if (EBoolean.YES.getCode().equals(isDefault)) {
            List<Splate> splateList = splateBO.queryIsDefaultSplateList(
                StringValidater.toInteger(EBoolean.YES.getCode()), companyCode);
            if (CollectionUtils.isNotEmpty(splateList)) {
                throw new BizException("xn0000", "该城市已有默认板块,请先取消在设置");
            }
        }
    }

    @Override
    public int dropSplate(String code) {
        Splate splate = splateBO.getSplate(code);
        if (EPlateStatus.ENABLE.getCode().equals(splate.getStatus())) {
            throw new BizException("xn0000", "小板块正在被使用，不可以删除");
        }
        List<Post> postList = postBO.queryPostList(code);
        if (CollectionUtils.isNotEmpty(postList)) {
            throw new BizException("xn0000", "小板块已被使用，不可以删除");
        }
        return splateBO.removeSplate(code);
    }

    @Override
    public Paginable<Splate> querySplatePage(int start, int limit,
            Splate condition) {
        Paginable<Splate> page = splateBO.getPaginable(start, limit, condition);
        List<Splate> splateList = page.getList();
        this.fullUser(splateList);
        return page;
    }

    @Override
    public List<Splate> querySplateList(Splate condition) {
        List<Splate> splateList = splateBO.querySplateList(condition);
        this.fullUser(splateList);
        return splateList;
    }

    public void fullUser(List<Splate> splateList) {
        for (Splate splate : splateList) {
            Integer allCommentNum = 0;
            Integer allLikeNum = 0;
            if (StringUtils.isNotBlank(splate.getModerator())) {
                User user = userBO.getRemoteUser(splate.getModerator());
                splate.setNickname(user.getNickname());
                splate.setMobile(user.getMobile());
            }
            Post pCondition = new Post();
            pCondition.setPlateCode(splate.getCode());
            pCondition.setStatus(EPostStatus.PUBLISHALL.getCode());
            List<Post> postList = postBO.queryPostList(pCondition);
            for (Post post : postList) {
                allCommentNum = allCommentNum + post.getSumComment();
                List<PostTalk> postTalkList = postTalkBO
                    .queryPostTalkSingleList(post.getCode(),
                        ETalkType.DZ.getCode(), null);
                if (CollectionUtils.isNotEmpty(postTalkList)) {
                    allLikeNum = allLikeNum + postTalkList.size();
                }
            }
            splate.setAllCommentNum(allCommentNum);
            splate.setAllLikeNum(allLikeNum);
        }
    }

    @Override
    public XN610046Res getSplate(String code) {
        XN610046Res res = new XN610046Res();
        Splate splate = splateBO.getSplate(code);
        Long allPostCount = postBO.getPostNum(splate.getCode(),
            EPostStatus.PUBLISHALL.getCode());
        Post condition = new Post();
        condition.setPlateCode(code);
        condition.setStatus(EPostStatus.PUBLISHALL.getCode());
        condition.setPublishDatetimeStart(DateUtil.getTodayStart());
        condition.setPublishDatetimeEnd(DateUtil.getTodayEnd());
        Long todayPostCount = postBO.getPostNum(condition);
        Long top = postBO.getPostLocation(ELocation.JH.getCode(),
            splate.getCompanyCode());
        Long essence = postBO.getPostLocation(ELocation.ZD.getCode(),
            splate.getCompanyCode());
        if (StringUtils.isNotBlank(splate.getModerator())) {
            User user = userBO.getRemoteUser(splate.getModerator());
            splate.setNickname(user.getNickname());
            splate.setMobile(user.getMobile());
        }
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
            BplateTemplate bplateTemplate = bplateTemplateBO
                .getBplateTemplate(splateTemplate.getBplateCode());
            Bplate condition = new Bplate();
            condition.setCompanyCode(companyCode);
            List<Bplate> bplateList = bplateBO.queryBplateList(condition);
            for (Bplate bplate : bplateList) {
                if (bplateTemplate.getOrderNo().equals(bplate.getOrderNo())) {
                    splateBO
                        .saveSplate(splateTemplate.getName(), bplate.getCode(),
                            splateTemplate.getPic(),
                            splateTemplate.getOrderNo(), null,
                            StringValidater.toInteger(EBoolean.NO.getCode()),
                            companyCode, EPlateStatus.ENABLE.getCode(),
                            splateTemplate.getUpdater(),
                            splateTemplate.getRemark());
                }
            }
        }
    }

    @Override
    public List<Splate> querySmallSplateList(Splate condition) {
        return splateBO.querySplateList(condition);
    }

    @Override
    public void defaultSplate(String code, String updater) {

        Splate splate = splateBO.getSplate(code);
        Integer isDefault = StringValidater.toInteger(EBoolean.YES.getCode());
        if (EBoolean.YES.getCode().equals(splate.getIsDefault())) {
            isDefault = StringValidater.toInteger(EBoolean.NO.getCode());
        }
        if (EBoolean.YES.getCode().equals(isDefault)) {
            List<Splate> splateList = splateBO.queryIsDefaultSplateList(
                StringValidater.toInteger(EBoolean.YES.getCode()),
                splate.getCompanyCode());
            if (CollectionUtils.isNotEmpty(splateList)) {
                throw new BizException("xn0000", "该城市已有默认板块,请先取消在设置");
            }
        }
        splateBO.defaultSplate(splate, isDefault, updater);
    }
}
