package com.std.forum.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IVideoAO;
import com.std.forum.bo.ICompanyBO;
import com.std.forum.bo.IVideoBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Video;
import com.std.forum.dto.res.XN001450Res;
import com.std.forum.enums.EVideoStatus;
import com.std.forum.exception.BizException;

@Service
public class VideoAOImpl implements IVideoAO {

    @Autowired
    private IVideoBO videoBO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addVideo(String name, String url, Integer orderNo,
            String updater, String remark, String companyCode) {
        return videoBO.saveVideo(name, url, orderNo, updater, remark,
            companyCode);
    }

    @Override
    public void editVideo(String code, String name, String url,
            Integer orderNo, String updater, String remark) {
        Video video = videoBO.getVideo(code);
        if (EVideoStatus.DOING.getCode().equals(video.getStatus())) {
            throw new BizException("xn0000", "正在上架的视频,不能修改");
        }
        videoBO.refreshVideo(video, name, url, orderNo, updater, remark);
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
        Video video = videoBO.getVideo(code);
        if (EVideoStatus.DOING.getCode().equals(video.getStatus())) {
            throw new BizException("xn0000", "该视频已上架,不可删除");
        }
        return videoBO.removeVideo(code);
    }

    @Override
    public Paginable<Video> queryVideoPage(int start, int limit, Video condition) {
        Paginable<Video> page = videoBO.getPaginable(start, limit, condition);
        List<Video> videoList = page.getList();
        for (Video video : videoList) {
            this.fullVideo(video);
        }
        return page;
    }

    @Override
    public List<Video> queryVideoList(Video condition) {
        List<Video> videoList = videoBO.queryVideoList(condition);
        for (Video video : videoList) {
            this.fullVideo(video);
        }
        return videoList;
    }

    @Override
    public Video getVideo(String code) {
        Video video = videoBO.getVideo(code);
        this.fullVideo(video);
        return video;
    }

    private void fullVideo(Video video) {
        XN001450Res res = companyBO.getCompany(video.getCompanyCode());
        video.setCompanyName(res.getName());
    }
}
