package com.std.forum.bo;

import java.util.List;

import com.std.forum.bo.base.IPaginableBO;
import com.std.forum.domain.Video;

public interface IVideoBO extends IPaginableBO<Video> {

    public boolean isVideoExist(String code);

    public String saveVideo(String name, Integer orderNo, String updater,
            String remark, String companyCode);

    public int removeVideo(String code);

    public int refreshVideo(Video video, String name, Integer orderNo,
            String updater, String remark);

    public List<Video> queryVideoList(Video condition);

    public Video getVideo(String code);

    public List<Video> queryVideoList(Integer orderNo);

    public void upVideo(String code, Integer orderNo, String updater,
            String remark);

    public void downVideo(String code, String updater, String remark);

}