package com.std.gym.dto.res;

import java.util.List;

import com.std.gym.domain.Comment;
import com.std.gym.domain.ItemScore;

/**
 * 详情查询评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:48:36 
 * @history:
 */
public class XN622146Res {
    private Comment comment;

    private List<ItemScore> itemScoreList;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<ItemScore> getItemScoreList() {
        return itemScoreList;
    }

    public void setItemScoreList(List<ItemScore> itemScoreList) {
        this.itemScoreList = itemScoreList;
    }
}
