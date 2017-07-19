package com.std.gym.dto.res;

import java.util.List;

import com.std.gym.domain.Coach;
import com.std.gym.domain.Comment;
import com.std.gym.domain.PerCourse;

/**
 * 详情查询私教(front)（带有私课和评论）
 * @author: asus 
 * @since: 2017年7月19日 上午11:15:23 
 * @history:
 */
public class XN622094Res {
    private Coach coach;

    private List<PerCourse> perCourseList;

    private List<Comment> commentList;

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<PerCourse> getPerCourseList() {
        return perCourseList;
    }

    public void setPerCourseList(List<PerCourse> perCourseList) {
        this.perCourseList = perCourseList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
