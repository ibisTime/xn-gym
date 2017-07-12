package com.std.gym.dto.req;

/**
 * 分页查询关键字
 * @author: asus 
 * @since: 2017年7月12日 下午2:45:39 
 * @history:
 */
public class XN622005Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 关键词(选填)
    private String word;

    // 作用等级(选填)
    private String level;

    // 反应(选填)
    private String reaction;

    // 更新人(选填)
    private String updater;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
