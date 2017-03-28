package com.std.forum.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IVideoAO;
import com.std.forum.bo.IVideoBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Video;
import com.std.forum.enums.EVideoStatus;
import com.std.forum.exception.BizException;

@Service
public class VideoAOImpl implements IVideoAO {

    @Autowired
    private IVideoBO videoBO;

    @Override
    public String addVideo(String name, Integer orderNo, String updater,
            String remark, String companyCode) {
        return videoBO.saveVideo(name, orderNo, updater, remark, companyCode);
    }

    @Override
    public void editVideo(String code, String name, Integer orderNo,
            String updater, String remark) {
        Video video = videoBO.getVideo(code);
        if (EVideoStatus.DOING.getCode().equals(video.getStatus())) {
            throw new BizException("xn0000", "正在上架的视频,不能修改");
        }
        videoBO.refreshVideo(video, name, orderNo, updater, remark);
    }

    @Override
    public void upVideo(String code, Integer orderNo, String updater,
            String remark) {
        Video video = videoBO.getVideo(code);
        if (EVideoStatus.DOING.getCode().equals(video.getStatus())) {
            throw new BizException("xn0000", "该视频已上架");
        }
        List<Video> videoList = videoBO.queryVideoList(orderNo);
        if (CollectionUtils.isNotEmpty(videoList)) {
            throw new BizException("xn0000", "顺序重复,请重新输入");
        }
        videoBO.upVideo(code, orderNo, updater, remark);
    }

    @Override
    public void downVideo(String code, String updater, String remark) {
        Video video = videoBO.getVideo(code);
        if (EVideoStatus.TODO.getCode().equals(video.getStatus())
                || EVideoStatus.DONE.getCode().equals(video.getStatus())) {
            throw new BizException("xn0000", "该视频未上架");
        }
        videoBO.downVideo(code, updater, remark);
    }

    @Override
    public int dropVideo(String code) {
        if (!videoBO.isVideoExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return videoBO.removeVideo(code);
    }

    @Override
    public Paginable<Video> queryVideoPage(int start, int limit, Video condition) {
        return videoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Video> queryVideoList(Video condition) {
        return videoBO.queryVideoList(condition);
    }

    @Override
    public Video getVideo(String code) {
        return videoBO.getVideo(code);
    }

}
