package com.std.gym.dto.req;

/**
 * 得分项目
 * @author: asus 
 * @since: 2017年7月19日 上午11:38:18 
 * @history:
 */
public class XN622200Req {
    // 编号（必填）
    private String ckey;

    // 分数（必填）
    private String score;

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
