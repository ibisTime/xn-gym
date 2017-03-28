package com.std.forum.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Video;

@Component
public interface IVideoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addVideo(String name, Integer orderNo, String updater,
            String remark, String companyCode);

    public int dropVideo(String code);

    public void editVideo(String code, String name, Integer orderNo,
            String updater, String remark);

    public void upVideo(String code, Integer orderNo, String updater,
            String remark);

    public void downVideo(String code, String updater, String remark);

    public Paginable<Video> queryVideoPage(int start, int limit, Video condition);

    public List<Video> queryVideoList(Video condition);

    public Video getVideo(String code);

}
