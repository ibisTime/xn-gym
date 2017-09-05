package com.std.gym.domain;

import com.std.gym.dao.base.ABaseDO;

/**
* 评论项目得分情况
* @author: xieyj 
* @since: 2017-07-13 16:50:48
* @history:
*/
public class ItemScore extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String id;

    // 好评项目名称
    private String name;

    // 得分
    private Integer score;

    // 评论编号
    private String commentCode;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode;
    }

    public String getCommentCode() {
        return commentCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
