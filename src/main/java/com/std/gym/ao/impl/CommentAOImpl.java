package com.std.gym.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.gym.ao.ICommentAO;
import com.std.gym.bo.ICoachBO;
import com.std.gym.bo.ICommentBO;
import com.std.gym.bo.IKeywordBO;
import com.std.gym.bo.IPerCourseBO;
import com.std.gym.bo.ISYSConfigBO;
import com.std.gym.bo.IUserBO;
import com.std.gym.bo.base.Paginable;
import com.std.gym.core.OrderNoGenerater;
import com.std.gym.core.StringValidater;
import com.std.gym.domain.Coach;
import com.std.gym.domain.Comment;
import com.std.gym.domain.PerCourse;
import com.std.gym.domain.SYSConfig;
import com.std.gym.dto.req.XN622200Req;
import com.std.gym.enums.ECommentStatus;
import com.std.gym.enums.EPrefixCode;
import com.std.gym.enums.EReaction;
import com.std.gym.exception.BizException;

@Service
public class CommentAOImpl implements ICommentAO {

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    private ICoachBO coachBO;

    @Autowired
    private IPerCourseBO perCourseBO;

    @Override
    public String addComment(String content, List<XN622200Req> itemScoreList,
            String commer, String productCode) {
        userBO.getRemoteUser(commer);
        String status = ECommentStatus.PUBLISHED.getCode();
        EReaction result = keywordBO.checkContent(content);
        if (EReaction.REFUSE.getCode().equals(result.getCode())) {
            status = ECommentStatus.FILTERED.getCode();
        }
        Comment data = new Comment();
        String code = OrderNoGenerater.generate(EPrefixCode.COMMENT.getCode());
        data.setCode(code);

        Double score = 0.0;
        Double num = 0.0;
        Integer dfScore = 0;
        if (productCode.startsWith(EPrefixCode.PERCOURSE.getCode())) {
            PerCourse perCourse = perCourseBO.getPerCourse(productCode);
            if (ECommentStatus.PUBLISHED.getCode().equals(status)) {
                for (XN622200Req req : itemScoreList) {
                    SYSConfig sysConfig = sysConfigBO.getConfig(StringValidater
                        .toLong(req.getCode()));
                    num = StringValidater.toInteger(req.getScore())
                            * StringValidater.toDouble(sysConfig.getCvalue());
                    score = score + num;
                }
                score = score / itemScoreList.size();
                dfScore = score.intValue();
                Coach coach = coachBO.getCoach(perCourse.getCoachCode());
                int starNum = coach.getStarNum() + dfScore;
                List<SYSConfig> sysConfigList = sysConfigBO
                    .querySYSConfigList("3");
                int star = coach.getStar();
                for (SYSConfig sysConfig : sysConfigList) {
                    if (starNum > StringValidater.toInteger(sysConfig
                        .getCvalue())) {
                        star = StringValidater.toInteger(sysConfig.getRemark());
                    }
                }
                coachBO.updateStar(coach, star, starNum);
            }
        }

        data.setScore(dfScore);
        data.setContent(content);
        data.setCommentDatetime(new Date());
        data.setProductCode(productCode);
        data.setStatus(status);

        commentBO.saveComment(data);
        return code;
    }

    @Override
    public void editComment(Comment data) {
        if (!commentBO.isCommentExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        commentBO.refreshComment(data);
    }

    @Override
    public void dropComment(String code) {
        if (!commentBO.isCommentExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        commentBO.removeComment(code);
    }

    @Override
    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition) {
        return commentBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentBO.queryCommentList(condition);
    }

    @Override
    public Comment getComment(String code) {
        return commentBO.getComment(code);
    }
}
