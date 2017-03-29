package com.std.forum.dto.res;

import com.std.forum.domain.Splate;

/**
 * 小版块详情查
 * @author: asus 
 * @since: 2017年3月27日 下午1:15:54 
 * @history:
 */
public class XN610046Res {
    private Splate splate;

    // 所有帖子数
    private Long allPostCount;

    // 今日发帖数
    private Long todayPostCount;

    // 置顶
    private Long top;

    // 精华
    private Long essence;

    public Splate getSplate() {
        return splate;
    }

    public void setSplate(Splate splate) {
        this.splate = splate;
    }

    public Long getAllPostCount() {
        return allPostCount;
    }

    public void setAllPostCount(Long allPostCount) {
        this.allPostCount = allPostCount;
    }

    public Long getTodayPostCount() {
        return todayPostCount;
    }

    public void setTodayPostCount(Long todayPostCount) {
        this.todayPostCount = todayPostCount;
    }

    public Long getTop() {
        return top;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public Long getEssence() {
        return essence;
    }

    public void setEssence(Long essence) {
        this.essence = essence;
    }
}
