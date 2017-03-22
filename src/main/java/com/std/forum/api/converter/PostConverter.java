package com.std.forum.api.converter;

import com.std.forum.common.DateUtil;
import com.std.forum.domain.Post;
import com.std.forum.dto.req.XN610130Req;
import com.std.forum.dto.req.XN610131Req;

public class PostConverter {

    // 分页查询帖子
    public static Post converter(XN610130Req req) {
        Post result = new Post();
        result.setUserId(req.getUserId());
        result.setTitle(req.getTitle());
        result.setKeyword(req.getKeyword());
        result.setPublisher(req.getPublisher());
        result.setStatus(req.getStatus());
        result.setIsLock(req.getIsLock());
        result.setLocation(req.getLocation());
        result.setPlateCode(req.getPlateCode());
        result.setCompanyCode(req.getCompanyCode());
        result.setPublishDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        result.setPublishDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        return result;
    }

    // 列表查询帖子
    public static Post converter(XN610131Req req) {
        Post result = new Post();
        result.setUserId(req.getUserId());
        result.setTitle(req.getTitle());
        result.setKeyword(req.getKeyword());
        result.setPublisher(req.getPublisher());
        result.setStatus(req.getStatus());
        result.setIsLock(req.getIsLock());
        result.setLocation(req.getLocation());
        result.setPlateCode(req.getPlateCode());
        result.setCompanyCode(req.getCompanyCode());
        result.setPublishDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        result.setPublishDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        return result;
    }
}
