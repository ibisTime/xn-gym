package com.std.forum.dto.res;

import com.std.forum.domain.BplateTemplate;
import com.std.forum.domain.SplateTemplate;

/**
 * 详情查询小板块模板
 * @author: asus 
 * @since: 2017年3月22日 下午5:47:01 
 * @history:
 */
public class XN610036Res {
    private SplateTemplate splateTemplate;

    private BplateTemplate bplateTemplate;

    public SplateTemplate getSplateTemplate() {
        return splateTemplate;
    }

    public void setSplateTemplate(SplateTemplate splateTemplate) {
        this.splateTemplate = splateTemplate;
    }

    public BplateTemplate getBplateTemplate() {
        return bplateTemplate;
    }

    public void setBplateTemplate(BplateTemplate bplateTemplate) {
        this.bplateTemplate = bplateTemplate;
    }
}
