package com.std.gym.dto.req;

/**
 * 修改私课
 * @author: asus 
 * @since: 2017年7月18日 下午4:29:30 
 * @history:
 */
public class XN622102Req {
    // 编号
    private String code;

    // 课程名称
    private String name;

    // 上课周期
    private String skCycle;

    // 上课时间
    private String skStartDatetime;

    // 下课时间
    private String skEndDatetime;

    // 缩略图
    private String pic;

    // 广告图
    private String advPic;

    // 描述
    private String description;

    // 价格
    private String price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkCycle() {
        return skCycle;
    }

    public void setSkCycle(String skCycle) {
        this.skCycle = skCycle;
    }

    public String getSkStartDatetime() {
        return skStartDatetime;
    }

    public void setSkStartDatetime(String skStartDatetime) {
        this.skStartDatetime = skStartDatetime;
    }

    public String getSkEndDatetime() {
        return skEndDatetime;
    }

    public void setSkEndDatetime(String skEndDatetime) {
        this.skEndDatetime = skEndDatetime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
