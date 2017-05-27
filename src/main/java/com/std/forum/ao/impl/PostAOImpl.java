/**
4 * @Title PostAOImpl.java 
 * @Package com.std.forum.ao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午7:52:36 
 * @version V1.0   
 */
package com.std.forum.ao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.forum.ao.IPostAO;
import com.std.forum.bo.IAccountBO;
import com.std.forum.bo.ICommentBO;
import com.std.forum.bo.ICompanyBO;
import com.std.forum.bo.IKeywordBO;
import com.std.forum.bo.ILevelRuleBO;
import com.std.forum.bo.IPostBO;
import com.std.forum.bo.IPostTalkBO;
import com.std.forum.bo.IRuleBO;
import com.std.forum.bo.ISplateBO;
import com.std.forum.bo.IUserBO;
import com.std.forum.bo.IUserExtBO;
import com.std.forum.bo.base.Page;
import com.std.forum.bo.base.Paginable;
import com.std.forum.common.DateUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Comment;
import com.std.forum.domain.Company;
import com.std.forum.domain.LevelRule;
import com.std.forum.domain.Post;
import com.std.forum.domain.PostTalk;
import com.std.forum.domain.Rule;
import com.std.forum.domain.Splate;
import com.std.forum.domain.User;
import com.std.forum.dto.res.XN610110Res;
import com.std.forum.dto.res.XN610124Res;
import com.std.forum.dto.res.XN805115Res;
import com.std.forum.enums.EBizType;
import com.std.forum.enums.EBoolean;
import com.std.forum.enums.EChannelType;
import com.std.forum.enums.ELocation;
import com.std.forum.enums.EPostStatus;
import com.std.forum.enums.EPostType;
import com.std.forum.enums.EReaction;
import com.std.forum.enums.ERuleKind;
import com.std.forum.enums.ERuleType;
import com.std.forum.enums.ESysAccount;
import com.std.forum.enums.ETalkType;
import com.std.forum.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 下午7:52:36 
 * @history:
 */
@Service
public class PostAOImpl implements IPostAO {

    @Autowired
    protected IPostBO postBO;

    @Autowired
    protected IPostTalkBO postTalkBO;

    @Autowired
    protected ICommentBO commentBO;

    @Autowired
    protected IKeywordBO keywordBO;

    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected ISplateBO splateBO;

    @Autowired
    protected IRuleBO ruleBO;

    @Autowired
    protected ILevelRuleBO levelRuleBO;

    @Autowired
    protected IAccountBO accountBO;

    @Autowired
    protected ICompanyBO companyBO;

    @Autowired
    protected IUserExtBO userExtBO;

    // 1.发布帖子
    // 判断是否发帖
    // 1、发帖，内容过滤，等级判断是否审核
    // 2、草稿保存
    @Override
    @Transactional
    public synchronized XN610110Res publishPost(String title, String content,
            String pic, String plateCode, String publisher, String isPublish) {
        XN610110Res res = new XN610110Res();
        // 判断版块是否存在
        Splate splate = splateBO.getSplate(plateCode);
        String code = null;
        User user = userBO.getRemoteUser(publisher);
        if (EBoolean.NO.getCode().equals(isPublish)) {// 保存草稿
            code = postBO.savePost(title, content, pic, splate.getCode(),
                publisher, user, EPostStatus.DRAFT.getCode());
            res.setCode(code);
            res.setAmount(0l);
        } else {// 直接发布
            res = doPublishPost(null, splate, title, content, pic, publisher,
                user);
        }
        return res;
    }

    // 2.草稿发布帖子
    @Override
    @Transactional
    public synchronized XN610110Res draftPublishPost(String code, String title,
            String content, String pic, String plateCode, String publisher,
            String isPublish) {
        XN610110Res res = new XN610110Res();
        // 判断版块是否存在
        Splate splate = splateBO.getSplate(plateCode);
        User user = userBO.getRemoteUser(publisher);
        if (EBoolean.NO.getCode().equals(isPublish)) {// 继续草稿
            postBO.refreshPost(code, title, content, pic, plateCode, publisher,
                user, EPostStatus.DRAFT.getCode());
            res.setCode(code);
            res.setAmount(0l);
        } else {
            res = doPublishPost(code, splate, title, content, pic, publisher,
                user);
        }
        return res;
    }

    private XN610110Res doPublishPost(String code, Splate splate, String title,
            String content, String pic, String publisher, User user) {
        String status = null;
        // 对标题和内容进行关键字过滤
        EReaction reaction1 = keywordBO.checkContent(title);
        EReaction reaction2 = keywordBO.checkContent(content);
        // 判断用户等级，是否审核
        LevelRule levelRule = levelRuleBO.getLevelRule(user.getLevel());
        if (EReaction.REFUSE.getCode().equals(reaction1.getCode())
                || EReaction.REFUSE.getCode().equals(reaction2.getCode())) {
            status = EPostStatus.FILTERED.getCode();
        } else {

            if (EBoolean.YES.getCode().equals(levelRule.getEffect())) {
                status = EPostStatus.todoAPPROVE.getCode();
            } else {
                status = EPostStatus.PUBLISHED.getCode();
            }
        }
        if (StringUtils.isNotBlank(code)) {
            postBO.refreshPost(code, title, content, pic, splate.getCode(),
                publisher, user, status);
        } else {
            code = postBO.savePost(title, content, pic, splate.getCode(),
                publisher, user, status);
        }
        // 告知前端被过滤了
        if (EPostStatus.FILTERED.getCode().equals(status)) {
            code = code + ";filter:true";
        }
        // 发帖加积分
        Rule rule = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.FT,
            user.getLevel());
        Long amount = 0l;
        if (EPostStatus.PUBLISHED.getCode().equals(status)) {
            accountBO.doTransferAmountRemote(ESysAccount.SYS_ACCOUNT.getCode(),
                publisher, EChannelType.JF,
                StringValidater.toLong(rule.getValue()), EBizType.AJ_TZFB,
                "发帖送赏金", "发帖送赏金");
            amount = accountBO.getAccountByUserId(publisher, EChannelType.JF);
            List<XN805115Res> LevelRuleList = queryLevelRuleList();
            for (XN805115Res res : LevelRuleList) {
                if (amount >= res.getAmountMin()
                        && amount <= res.getAmountMax()) {
                    userBO.upgradeLevel(publisher, res.getCode());
                    break;
                }
            }
        }
        XN610110Res res = new XN610110Res();
        res.setCode(code);
        res.setAmount(amount);
        return res;
    }

    private List<XN805115Res> queryLevelRuleList() {
        return levelRuleBO.queryLevelRuleList();
    }

    @Override
    @Transactional
    public void dropPostList(List<String> codeList, String userId, String type) {
        for (String code : codeList) {
            dropPost(code, userId, type);
        }
    }

    @Override
    @Transactional
    public void dropPost(String code, String userId, String type) {
        Post post = null;
        Splate splate = null;
        Comment comment = null;
        String publisher = null;
        if (EPostType.PL.getCode().equals(type)) {
            comment = commentBO.getComment(code);
            publisher = comment.getCommer();
            post = postBO.getPost(comment.getPostCode());
        } else {
            post = postBO.getPost(code);
            publisher = post.getPublisher();
            List<PostTalk> talkList = postTalkBO.queryPostTalkSingleList(
                post.getCode(), null, null);
            for (PostTalk postTalk : talkList) {
                postTalkBO.removePostTalk(postTalk.getCode());
            }
        }
        splate = splateBO.getSplate(post.getPlateCode());
        String companyCode = splate.getCompanyCode();
        User res = userBO.getRemoteUser(userId);
        // if (EUserKind.Operator.getCode().equals(res.getKind())) {
        // if (!companyCode.equals(res.getCompanyCode())) {
        // throw new BizException("xn000000", "当前用户不是该帖子的管理员，无法删除");
        // }
        // } else {
        List<Splate> plateList = splateBO.getPlateByUserId(userId);
        Map<String, Splate> map = new HashMap<String, Splate>();
        for (Splate data : plateList) {
            map.put(data.getCode(), data);
        }
        if (userId.equals(publisher) || res.getKind().equals("01")
                || userId.equals(splate.getModerator())) {
            if (EPostType.TZ.getCode().equals(type)) {
                postBO.removePost(code);
                // 删除帖子相关的评论
                commentBO.removeCommentByPost(code);
            } else if (EPostType.PL.getCode().equals(type)) {
                commentBO.removeComment(code);
                // 删除下级，下下级评论
                List<Comment> commentList = new ArrayList<Comment>();
                searchCycleComment(code, commentList, null);
                for (Comment data : commentList) {
                    commentBO.removeComment(data.getCode());
                }
            }
        } else {
            throw new BizException("xn000000", "当前用户不是该版块版主或发布用户，无法删除");
        }
        // }

    }

    private void searchCycleComment(String parentCode, List<Comment> list,
            String status) {
        List<Comment> nextList = commentBO.queryCommentList(parentCode, status);
        if (CollectionUtils.isNotEmpty(nextList)) {
            list.addAll(nextList);
            for (int i = 0; i < nextList.size(); i++) {
                searchCycleComment(nextList.get(i).getCode(), list, status);
            }
        }
    }

    @Override
    @Transactional
    public void setPostLocation(String code, String location, Integer orderNo,
            String updater) {
        Post post = postBO.getPost(code);
        String[] locationArr = { "A", "B", "C", "D" };
        String newLocation = "";
        for (String locationStr : locationArr) {
            if (location.contains(locationStr)) {
                newLocation += locationStr + ",";
            }
        }
        newLocation = newLocation.substring(0, newLocation.length() - 1);
        postBO.refreshPostLocation(code, newLocation, orderNo, updater);
        String[] locationArrary = location.split(",");
        User user = userBO.getRemoteUser(post.getPublisher());
        Rule rule = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.JH,
            user.getLevel());
        String[] oldLocation = post.getLocation().split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < oldLocation.length; i++) {
            map.put(oldLocation[i], oldLocation[i]);
        }
        String bizNote = null;
        for (String JH : locationArrary) {
            if (JH.equals(ELocation.ALL.getCode())) {
                return;
            }
            // 设置精华加积分(如何判断前面是否重复加)
            if (ELocation.JH.getCode().equals(JH) && !map.containsKey(JH)) {
                bizNote = "精华帖送赏金";
                accountBO.doTransferAmountRemote(
                    ESysAccount.SYS_ACCOUNT.getCode(), post.getPublisher(),
                    EChannelType.JF, StringValidater.toLong(rule.getValue()),
                    EBizType.AJ_JHT, bizNote, bizNote);
            }
            if (ELocation.ZD.getCode().equals(JH) && !map.containsKey(JH)) {
                bizNote = "置顶送赏金";
                accountBO.doTransferAmountRemote(
                    ESysAccount.SYS_ACCOUNT.getCode(), post.getPublisher(),
                    EChannelType.JF, StringValidater.toLong(rule.getValue()),
                    EBizType.AJ_ZDT, bizNote, bizNote);
            }
            if (ELocation.TT.getCode().equals(JH) && !map.containsKey(JH)) {
                bizNote = "头条送赏金";
                accountBO.doTransferAmountRemote(
                    ESysAccount.SYS_ACCOUNT.getCode(), post.getPublisher(),
                    EChannelType.JF, StringValidater.toLong(rule.getValue()),
                    EBizType.AJ_TTT, bizNote, bizNote);
            }
            Long amount = accountBO.getAccountByUserId(post.getPublisher(),
                EChannelType.JF);
            List<XN805115Res> LevelRuleList = queryLevelRuleList();
            for (XN805115Res res : LevelRuleList) {
                if (amount >= res.getAmountMin()
                        && amount <= res.getAmountMax()) {
                    userBO.upgradeLevel(post.getPublisher(), res.getCode());
                    break;
                }
            }
        }
    }

    // 批量审核帖子
    @Override
    public void approvePostList(List<String> codeList, String approver,
            String approveResult, String approveNote, String type) {
        for (String code : codeList) {
            this.approvePost(code, approver, approveResult, approveNote, type);
        }
    }

    public void approvePost(String code, String approver, String approveResult,
            String approveNote, String type) {
        if (EPostType.TZ.getCode().equals(type)) {
            Post post = postBO.getPost(code);
            User user = userBO.getRemoteUser(post.getPublisher());
            Rule rule = ruleBO.getRuleByCondition(ERuleKind.JF, ERuleType.FT,
                user.getLevel());
            Rule rule1 = ruleBO.getRuleByCondition(ERuleKind.JF,
                ERuleType.TZWG, user.getLevel());
            if (EBoolean.YES.getCode().equals(approveResult)
                    && !EPostStatus.todoAPPROVE.getCode().equals(
                        post.getStatus())
                    && !EPostStatus.toReportAPPROVE.getCode().equals(
                        post.getStatus())) {
                throw new BizException("xn000000", "帖子不是待审核状态");
            }
            postBO.refreshPostApprove(code, approver, approveResult,
                approveNote);
            // 审核通过加积分
            if (EPostStatus.todoAPPROVE.getCode().equals(post.getStatus())
                    && EBoolean.YES.getCode().equals(approveResult)) {

                accountBO.doTransferAmountRemote("SYS_ACCOUNT",
                    post.getPublisher(), EChannelType.JF,
                    StringValidater.toLong(rule.getValue()), EBizType.AJ_TZFB,
                    "帖子审核通过送赏金", "帖子审核通过送赏金");
                Long amount = accountBO.getAccountByUserId(post.getPublisher(),
                    EChannelType.JF);
                List<XN805115Res> LevelRuleList = queryLevelRuleList();
                for (XN805115Res res : LevelRuleList) {
                    if (amount >= res.getAmountMin()
                            && amount <= res.getAmountMax()) {
                        userBO.upgradeLevel(post.getPublisher(), res.getCode());
                        break;
                    }
                }
            }
            // 被举报，确认存在问题，扣积分
            if (EPostStatus.toReportAPPROVE.getCode().equals(post.getStatus())
                    && EBoolean.NO.getCode().equals(approveResult)) {
                accountBO.doTransferAmountRemote(post.getPublisher(),
                    ESysAccount.SYS_ACCOUNT.getCode(), EChannelType.JF,
                    StringValidater.toLong(rule1.getValue()), EBizType.AJ_TZWG,
                    "帖子确认存在问题，扣赏金", "帖子确认存在问题，扣赏金");
            }
        } else if (EPostType.PL.getCode().equals(type)) {
            type = ETalkType.PLJB.getCode();
            Comment comment = commentBO.getComment(code);
            User user1 = userBO.getRemoteUser(comment.getCommer());
            Rule rule2 = ruleBO.getRuleByCondition(ERuleKind.JF,
                ERuleType.PLWG, user1.getLevel());
            Post parentPost = postBO.getPost(comment.getPostCode());
            if (!EPostStatus.todoAPPROVE.getCode().equals(comment.getStatus())
                    && !EPostStatus.toReportAPPROVE.getCode().equals(
                        comment.getStatus())) {
                throw new BizException("xn000000", "评论状态不是待审核状态");
            }
            if (EPostStatus.todoAPPROVE.getCode().equals(comment.getStatus())
                    && EBoolean.YES.getCode().equals(approveResult)) {
                accountBO.doTransferAmountRemote(comment.getCommer(),
                    ESysAccount.SYS_ACCOUNT.getCode(), EChannelType.JF,
                    StringValidater.toLong(rule2.getValue()), EBizType.AJ_PLFB,
                    "评论送赏金", "评论送赏金");
                postBO.refreshPostSumComment(parentPost.getCode(),
                    parentPost.getSumComment() + 1);
                Long amount = accountBO.getAccountByUserId(comment.getCommer(),
                    EChannelType.JF);
                List<XN805115Res> LevelRuleList = queryLevelRuleList();
                for (XN805115Res res : LevelRuleList) {
                    if (amount >= res.getAmountMin()
                            && amount <= res.getAmountMax()) {
                        userBO.upgradeLevel(comment.getCommer(), res.getCode());
                        break;
                    }
                }
            }
            commentBO.refreshCommentApprove(code, approveResult, approver,
                approveNote);
            // 被举报，确认存在问题，扣积分
            if (EPostStatus.toReportAPPROVE.getCode().equals(
                comment.getStatus())
                    && EBoolean.NO.getCode().equals(approveResult)) {
                accountBO.doTransferAmountRemote(comment.getCommer(),
                    ESysAccount.SYS_ACCOUNT.getCode(), EChannelType.JF,
                    StringValidater.toLong(rule2.getValue()), EBizType.AJ_PLWG,
                    "确认评论违规，扣赏金", "确认评论违规，扣赏金");
                // 确认存在问题，减一次评论数
                postBO.refreshPostSumComment(parentPost.getCode(),
                    parentPost.getSumComment() - 1);
            }
        }
    }

    // 是否锁帖
    @Override
    public void lockPost(List<String> codeList) {
        for (String code : codeList) {
            Post post = postBO.getPost(code);
            // 1 锁帖 0 正常帖
            boolean flag = false;
            if (EBoolean.YES.getCode().equals(post.getIsLock())) {
                flag = true;
            }
            postBO.refreshPostLock(code, flag);
        }
    }

    @Override
    public void editPostPlate(List<String> codeList, String plateCode) {
        for (String code : codeList) {
            postBO.getPost(code);
            Splate splate = splateBO.getSplate(plateCode);
            if (EBoolean.NO.getCode().equals(splate.getStatus())) {
                throw new BizException("xn000000", "该版块状态为未启用");
            }
            postBO.refreshPostPlate(code, plateCode);
        }
    }

    // 分页查
    @Override
    public Paginable<Post> queryPostPage(int start, int limit, Post condition) {
        if (StringUtils.isNotBlank(condition.getPlateCode())) {
            Splate splate = splateBO.getSplate(condition.getPlateCode());
            if (!splate.getCompanyCode().equals(condition.getCompanyCode())) {
                condition.setCompanyCode(splate.getCompanyCode());
            }
        }
        condition.setLocation(setLocation(condition.getLocation()));
        Paginable<Post> postPage = postBO.getPaginable(start, limit, condition);
        List<Post> postList = postPage.getList();
        // 帖子优化
        // 1、postCode 设置成list,查所有评论，所有点赞
        for (Post post : postList) {
            cutPic(post);
            this.fullPost(post);
            this.fullIsDZ(post, condition.getUserId());
            this.fullIsSC(post, condition.getUserId());
        }
        return postPage;
    }

    private void fullIsSC(Post post, String userId) {
        post.setIsSC(EBoolean.NO.getCode());
        List<PostTalk> postTalkList = postTalkBO.queryPostTalkSingleList(
            post.getCode(), ETalkType.SC.getCode(), userId);
        if (CollectionUtils.isNotEmpty(postTalkList)) {
            post.setIsSC(EBoolean.YES.getCode());
        }
    }

    private void fullIsDZ(Post post, String userId) {
        post.setIsDZ(EBoolean.NO.getCode());
        List<PostTalk> postTalkList = postTalkBO.queryPostTalkSingleList(
            post.getCode(), ETalkType.DZ.getCode(), userId);
        if (CollectionUtils.isNotEmpty(postTalkList)) {
            post.setIsDZ(EBoolean.YES.getCode());
        }
    }

    // 列表查
    @Override
    public List<Post> queryPostList(Post condition) {
        condition.setLocation(setLocation(condition.getLocation()));
        List<Post> postList = postBO.queryPostList(condition);
        for (Post post : postList) {
            this.cutPic(post);
            this.fullPost(post);
            this.fullIsDZ(post, condition.getUserId());
            this.fullIsSC(post, condition.getUserId());
        }
        return postList;
    }

    private void fullPost(Post post) {
        List<Comment> commentList = commentBO.queryCommentLimitList(post
            .getCode());
        List<PostTalk> postTalkList = postTalkBO.queryPostTalkLimitList(post
            .getCode());
        post.setCommentList(commentList);
        post.setLikeList(postTalkList);
    }

    private String setLocation(String location) {
        if (StringUtils.isNotBlank(location)) {
            String[] desc = location.split(",");
            for (int i = 0; i < desc.length; i++) {
                location = desc[i] + "%";
            }
        }
        return location;
    }

    private void fullSplate(Post post) {
        Splate splate = splateBO.getSplate(post.getPlateCode());
        post.setPlateName(splate.getName());
    }

    @Override
    public Post getPost(String code, String userId, String commStatus) {
        Post post = postBO.getPost(code);
        this.cutPic(post);
        this.getPartInfo(post, userId);
        // this.fullGetPost(post);
        return post;
    }

    private void fullGetPost(Post post) {
        Comment condition = new Comment();
        condition.setParentCode(post.getCode());
        List<Comment> commentList = commentBO.queryCommentList(condition);
        post.setCommentList(commentList);
        PostTalk iPostTalk = new PostTalk();
        condition.setParentCode(post.getCode());
        List<PostTalk> postTalkList = postTalkBO.queryPostTalkList(iPostTalk);
        post.setLikeList(postTalkList);
    }

    @Override
    public Post getPost(String code) {
        Post post = postBO.getPost(code);
        this.cutPic(post);
        List<PostTalk> postTalkList = postTalkBO.queryPostTalkSingleList(
            post.getCode(), ETalkType.TZJB.getCode(), null);
        post.setReportNum(postTalkList.size());
        post.setLikeList(postTalkList);
        return post;
    }

    private void cutPic(Post post) {
        String pic = post.getPic();
        if (StringUtils.isNotBlank(pic)) {
            String[] picArr = pic.split("\\|\\|");
            post.setPicArr(picArr);
            post.setPic(null);
        }
    }

    /**
     * 获取数据
     * @param post
     * @param userId
     * @param commStatus
     * @param size 
     * @create: 2017年3月8日 下午1:46:33 xieyj
     * @history:
     */
    private void getPartInfo(Post post, String userId) {
        String code = post.getCode();
        // 设置查询点赞记录条件
        post.setIsDZ(EBoolean.NO.getCode());
        post.setIsSC(EBoolean.NO.getCode());
        if (StringUtils.isNotBlank(userId)) {
            PostTalk dzPostTalk = postTalkBO.getPostTalkByCondition(code,
                userId, ETalkType.DZ.getCode());
            if (null != dzPostTalk) {
                post.setIsDZ(EBoolean.YES.getCode());
            }
            PostTalk scPostTalk = postTalkBO.getPostTalkByCondition(code,
                userId, ETalkType.SC.getCode());
            if (null != scPostTalk) {
                post.setIsSC(EBoolean.YES.getCode());
            }
        }
    }

    private void orderCommentList(List<Comment> commentList) {
        for (int i = 0; i < commentList.size(); i++) {
            for (int j = i + 1; j < commentList.size(); j++) {
                if (commentList.get(i).getCommDatetime().getTime() > commentList
                    .get(j).getCommDatetime().getTime()) {
                    Comment temp = new Comment();
                    temp = commentList.get(i);
                    commentList.set(i, commentList.get(j));
                    commentList.set(j, temp);
                }
            }
        }
    }

    @Override
    public Paginable<Post> querySCPostPage(int start, int limit, Post condition) {
        condition.setType(ETalkType.SC.getCode());
        Paginable<Post> postPage = null;
        List<Post> list = postBO.selectSCList(condition);
        postPage = new Page<Post>(start, limit, list.size());
        List<Post> dataList = postBO.queryPostSCList(condition,
            postPage.getStart(), postPage.getPageSize());
        postPage.setList(dataList);
        List<Post> postList = postPage.getList();
        for (Post post : postList) {
            cutPic(post);
            this.getPartInfo(post, condition.getUserId());
            this.fullPost(post);
            this.fullSplate(post);
        }
        return postPage;
    }

    @Override
    public List<Post> querySCPostList(String talker) {
        Post condition = new Post();
        condition.setType(ETalkType.SC.getCode());
        condition.setTalker(talker);
        List<Post> postList = postBO.selectSCList(condition);
        for (Post post : postList) {
            this.fullSplate(post);
            this.cutPic(post);
            this.getPartInfo(post, condition.getUserId());
            this.fullPost(post);
        }
        return postList;
    }

    /** 
     * @see com.std.forum.ao.IPostAO#readPost(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void readPost(String postCode) {
        Post post = postBO.getPost(postCode);
        postBO.refreshPostSumRead(postCode, post.getSumRead() + 1);
    }

    @Override
    public Post getPostByCommentCode(String commentCode, String userId) {
        Post post = null;
        Comment comment = commentBO.getComment(commentCode);
        post = postBO.getPost(comment.getPostCode());
        getPartInfo(post, userId);
        return post;
    }

    @Override
    public Long getMyPostCount(String userId, String status) {
        return postBO.getMyPostCount(userId, status);
    }

    @Override
    public void returnPost(List<String> codeList, String type) {
        for (String code : codeList) {
            if (EPostType.TZ.getCode().equals(type)) {
                Post post = postBO.getPost(code);
                if (!EPostStatus.APPROVE_NO.getCode().equals(post.getStatus())) {
                    throw new BizException("xn000000", "该帖子不是待回收状态");
                }
                postBO.refreshPostReturn(code);
            } else {
                Comment comment = commentBO.getComment(code);
                if (!EPostStatus.APPROVE_NO.getCode().equals(
                    comment.getStatus())) {
                    throw new BizException("xn000000", "该评论不是待回收状态");
                }
                commentBO.refreshCommentReturn(code);
            }
        }
    }

    @Override
    public Paginable<Post> queryTDPostPage(int start, int limit,
            Post condition, String userId) {
        Paginable<Post> page = null;
        User user = userBO.getRemoteUser(userId);
        condition.setKeyword("@" + user.getNickname());
        List<Post> List = postBO.selectTDList(condition);
        page = new Page<Post>(start, limit, List.size());
        List<Post> dataList = postBO.queryTDPostList(condition,
            page.getStart(), page.getPageSize());
        page.setList(dataList);
        List<Post> list = page.getList();
        List<String> postCodeList = new ArrayList<String>();
        for (Post post : list) {
            cutPic(post);
            this.getPartInfo(post, condition.getUserId());
            postCodeList.add(post.getCode());
            this.fullPost(post);
            this.fullSplate(post);
        }
        return page;
    }

    @Override
    public Paginable<Post> queryOSSPostPage(int start, int limit, Post condition) {
        condition.setLocation(setLocation(condition.getLocation()));
        Paginable<Post> postPage = postBO.getPaginable(start, limit, condition);
        List<Post> postList = postPage.getList();
        for (Post post : postList) {
            cutPic(post);
            List<PostTalk> postTalkList = postTalkBO.queryPostTalkSingleList(
                post.getCode(), ETalkType.TZJB.getCode(), null);
            post.setReportNum(postTalkList.size());
        }
        return postPage;
    }

    // 修改标题
    @Override
    public void updateTitle(String code, String title, String userId,
            String remark) {
        User user = userBO.getRemoteUser(userId);
        Post post = postBO.getPost(code);
        // 对标题进行关键字过滤
        EReaction reaction1 = keywordBO.checkContent(title);
        if (EReaction.REFUSE.getCode().equals(reaction1.getCode())) {
            throw new BizException("xn000000", "标题包含敏感字");
        }
        postBO.updatePostTitle(post, user, title, remark);
    }

    @Override
    public XN610124Res getTotal(String companyCode) {
        Long ztTotal = 0l;
        Long jtTotal = 0l;
        Long qbTotal = 0l;
        Long maxRead = 0l;
        Long sumRead = 0l;
        Double avgRead = 0.00;
        Integer userTotal = 0;
        // 查询昨天发布的帖子
        Post post = new Post();
        post.setPublishDatetimeStart(DateUtil.getYesterdayStart());
        post.setPublishDatetimeEnd(DateUtil.getYesterdayEnd());
        post.setCompanyCode(companyCode);
        ztTotal = postBO.getPostNum(post);
        // 查询今天发布的帖子
        Post data = new Post();
        data.setPublishDatetimeStart(DateUtil.getTodayStart());
        data.setPublishDatetimeEnd(DateUtil.getTodayEnd());
        data.setCompanyCode(companyCode);
        jtTotal = postBO.getPostNum(data);
        // 查询全部帖子
        Post qbPost = new Post();
        qbPost.setCompanyCode(companyCode);
        qbTotal = postBO.getPostNum(qbPost);
        Post maxReadPost = postBO.selectMaxRead(companyCode);
        if (maxReadPost == null) {
            maxRead = 0l;
        } else {
            maxRead = StringValidater.toLong(maxReadPost.getMaxRead());
        }
        Post sumReadPost = postBO.selectSumRead(companyCode);
        if (sumReadPost == null) {
            sumRead = 0l;
        } else {
            sumRead = StringValidater.toLong(sumReadPost.getSunRead());
        }
        // Double sunRead = (double) sumRead;
        // Double qbtotal = (double) qbTotal;
        // avgRead = sunRead / qbtotal;
        // if (sunRead == 0.0) {
        // avgRead = 0.0;
        // } else if (qbtotal == 0.0) {
        // avgRead = 0.0;
        // }
        userTotal = userBO.userTotal(companyCode);
        XN610124Res res = new XN610124Res();
        res.setZtTotal(ztTotal);
        res.setJtTotal(jtTotal);
        res.setQbTotal(qbTotal);
        // res.setMaxRead(maxRead);
        // res.setAvgRead(avgRead);
        res.setSumRead(sumRead);
        res.setUserTotal(userTotal);
        return res;
    }

    @Override
    public Paginable<XN610124Res> queryTotalPage(int start, int limit,
            Post condition) {
        Paginable<XN610124Res> page = null;
        List<XN610124Res> postList = new ArrayList<XN610124Res>();
        Long ztTotal = 0l;
        Long jtTotal = 0l;
        Long qbTotal = 0l;
        Long maxRead = 0l;
        Long sumRead = 0l;
        Double avgRead = 0.00;
        Integer userTotal = 0;
        if (StringUtils.isBlank(condition.getCompanyCode())) {
            List<Company> companyList = companyBO.queryCompanyList();
            for (Company company : companyList) {
                // 查询昨天发布的帖子
                Post post = new Post();
                post.setPublishDatetimeStart(DateUtil.getYesterdayStart());
                post.setPublishDatetimeEnd(DateUtil.getYesterdayEnd());
                post.setCompanyCode(company.getCode());
                ztTotal = postBO.getPostNum(post);
                // 查询今天发布的帖子
                Post data = new Post();
                data.setPublishDatetimeStart(DateUtil.getTodayStart());
                data.setPublishDatetimeEnd(DateUtil.getTodayEnd());
                data.setCompanyCode(company.getCode());
                jtTotal = postBO.getPostNum(data);
                // 查询全部帖子
                Post qbPost = new Post();
                qbPost.setCompanyCode(company.getCode());
                qbTotal = postBO.getPostNum(qbPost);
                Post maxReadPost = postBO.selectMaxRead(company.getCode());
                if (maxReadPost == null) {
                    maxRead = 0l;
                } else {
                    maxRead = StringValidater.toLong(maxReadPost.getMaxRead());
                }
                Post sumReadPost = postBO.selectSumRead(company.getCode());
                if (sumReadPost == null) {
                    sumRead = 0l;
                } else {
                    sumRead = StringValidater.toLong(sumReadPost.getSunRead());
                }
                // Double sunRead = (double) sumRead;
                // Double qbtotal = (double) qbTotal;
                // avgRead = sunRead / qbtotal;
                // if (sunRead == 0.0) {
                // avgRead = 0.0;
                // } else if (qbtotal == 0.0) {
                // avgRead = 0.0;
                // }
                userTotal = userBO.userTotal(company.getCode());
                XN610124Res res = new XN610124Res();
                res.setZtTotal(ztTotal);
                res.setJtTotal(jtTotal);
                res.setQbTotal(qbTotal);
                // res.setMaxRead(maxRead);
                // res.setAvgRead(avgRead);
                res.setSumRead(sumRead);
                res.setUserTotal(userTotal);
                res.setCompanyName(company.getName());
                postList.add(res);
            }
        }
        page = new Page<XN610124Res>(start, limit, postList.size());
        if (postList.size() < limit) {
            limit = postList.size();
        }
        List<XN610124Res> dataList = postList.subList(start - 1, limit);
        page.setList(dataList);
        return page;
    }

    // pc端查询
    @Override
    public Paginable<Post> queryPcPostPage(int start, int limit, Post condition) {
        Paginable<Post> postPage = null;
        List<Post> list = postBO.selectPcList(condition);
        postPage = new Page<Post>(start, limit, list.size());
        List<Post> dataList = postBO.queryPostPcList(condition,
            postPage.getStart(), postPage.getPageSize());
        postPage.setList(dataList);
        List<Post> postList = postPage.getList();
        // 帖子优化
        // 1、postCode 设置成list,查所有评论，所有点赞
        for (Post post : postList) {
            cutPic(post);
            this.fullPost(post);
            this.fullIsDZ(post, condition.getUserId());
            this.fullIsSC(post, condition.getUserId());
        }
        return postPage;
    }

    @Override
    public void modifyUser(String userId, String nickname, String gender,
            String birthday, String photo, String email, String introduce) {
        // 修改头像
        userExtBO.refreshUserPhoto(userId, photo);
        // 修改用户信息
        userExtBO.refreshUserExt(userId, photo, gender, birthday, email,
            introduce);
        // 修改用户昵称
        userExtBO.refreshNickname(userId, nickname);
        postBO.updateUserInf(userId, nickname, photo);
        commentBO.updateUserInf(userId, nickname, photo);
        postTalkBO.updateUserInf(userId, nickname, photo);
    }

    @Override
    public void modifyUser(String userId, String loginName) {
        userExtBO.refreshLoginName(userId, loginName);
        postBO.updateUserInf(userId, loginName);
        commentBO.updateUserInf(userId, loginName);
        postTalkBO.updateUserInf(userId, loginName);
    }
}
