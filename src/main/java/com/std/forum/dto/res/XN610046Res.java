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

    private Long allPostCount;

    private Long todayPostCount;

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
}
