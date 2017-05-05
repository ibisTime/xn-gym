package com.std.forum.dto.res;

public class XN610124Res {
    // 昨天帖子总数
    private Long ztTotal;

    // 今天帖子总数
    private Long jtTotal;

    // 帖子总数
    private Long qbTotal;

    // 最大阅读数
    private Long maxRead;

    // 平均阅读数
    private Long avgRead;

    public Long getZtTotal() {
        return ztTotal;
    }

    public void setZtTotal(Long ztTotal) {
        this.ztTotal = ztTotal;
    }

    public Long getJtTotal() {
        return jtTotal;
    }

    public void setJtTotal(Long jtTotal) {
        this.jtTotal = jtTotal;
    }

    public Long getQbTotal() {
        return qbTotal;
    }

    public void setQbTotal(Long qbTotal) {
        this.qbTotal = qbTotal;
    }

    public Long getMaxRead() {
        return maxRead;
    }

    public void setMaxRead(Long maxRead) {
        this.maxRead = maxRead;
    }

    public Long getAvgRead() {
        return avgRead;
    }

    public void setAvgRead(Long avgRead) {
        this.avgRead = avgRead;
    }

}
