package com.std.gym.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.gym.ao.ICommentAO;
import com.std.gym.bo.IAccountBO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.IKeywordBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Comment;
import com.std.gym.domain.User;
import com.std.gym.enums.EBizType;
import com.std.gym.enums.EChannelType;
import com.std.gym.enums.ECommentStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.EReaction;
import com.std.gym.enums.ESysAccount;

@Service
public class CommentAOImpl implements ICommentAO {

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected IAccountBO accountBO;

    // 1.发布评论
    @Override
    @Transactional
    public String doComment(String content, String parentCode, String commer) {
        User user = userBO.getRemoteUser(commer);
        // 判断非法词汇,无则正常发布
        String status = null;
        EReaction result = keywordBO.checkContent(content);
        if (EReaction.REFUSE.getCode().equals(result.getCode())) {
            status = ECommentStatus.FILTERED.getCode();
        }
        String code = commentBO.saveComment(content, parentCode, status, user,
            parentCode);
        // 评论送钱
        if (ECommentStatus.PUBLISHED.getCode().equals(status)) {
            accountBO.doTransferAmountRemote(
                ESysAccount.SYS_USER_ZWZJ.getCode(), commer, EChannelType.JF,
                StringValidater.toLong(parentCode), EBizType.AJ_PLFB,
                "发布评论，送赏金", "发布评论，送赏金");
            accountBO.getAccountByUserId(commer, EChannelType.JF);
        } else {
            code = code + ";filter:true";
        }
        return code;
    }

    @Override
    public Comment getComment(String code) {
        Comment comment = commentBO.getComment(code);
        getParentComment(comment);
        return comment;
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentBO.queryCommentList(condition);
    }

    @Override
    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition) {
        Paginable<Comment> page = commentBO.getPaginable(start, limit,
            condition);
        List<Comment> list = page.getList();
        for (Comment comment : list) {
            getParentComment(comment);
        }
        return page;
    }

    private void getParentComment(Comment comment) {
        String parentCode = comment.getParentCode();
        if (EPrefixCode.COMMENT.getCode().equals(parentCode.substring(0, 2))) {
            Comment data = commentBO.getComment(parentCode);
            comment.setParentComment(data);
        }
    }

}
