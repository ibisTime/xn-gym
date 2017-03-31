package com.std.forum.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.IVideoBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.OrderNoGenerater;
import com.std.forum.dao.IVideoDAO;
import com.std.forum.domain.Video;
import com.std.forum.enums.EPrefixCode;
import com.std.forum.enums.EVideoStatus;
import com.std.forum.exception.BizException;

@Component
public class VideoBOImpl extends PaginableBOImpl<Video> implements IVideoBO {

    @Autowired
    private IVideoDAO videoDAO;

    @Override
    public String saveVideo(String name, String pic, String url,
            Integer orderNo, String updater, String remark, String companyCode) {
        Video data = new Video();
        String code = OrderNoGenerater.generateME(EPrefixCode.VIDEO.getCode());
        data.setCode(code);
        data.setName(name);
        data.setPic(pic);
        data.setUrl(url);
        data.setOrderNo(orderNo);
        data.setStatus(EVideoStatus.TODO.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        data.setCompanyCode(companyCode);
        videoDAO.insert(data);
        return code;
    }

    @Override
    public int removeVideo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Video data = new Video();
            data.setCode(code);
            count = videoDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshVideo(Video video, String name, String pic, String url,
            Integer orderNo, String updater, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(video.getCode())) {
            Video data = new Video();
            data.setCode(video.getCode());
            data.setName(name);
            data.setPic(pic);
            data.setUrl(url);
            data.setOrderNo(orderNo);
            data.setStatus(video.getStatus());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            count = videoDAO.update(data);
        }
        return count;
    }

    @Override
    public void upVideo(String code, Integer orderNo, String updater,
            String remark) {
        Video data = new Video();
        data.setCode(code);
        data.setOrderNo(orderNo);
        data.setStatus(EVideoStatus.DOING.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        videoDAO.updateUpVideo(data);
    }

    @Override
    public void downVideo(String code, String updater, String remark) {
        Video data = new Video();
        data.setCode(code);
        data.setStatus(EVideoStatus.DONE.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        videoDAO.updateDownVideo(data);
    }

    @Override
    public List<Video> queryVideoList(Video condition) {
        return videoDAO.selectList(condition);
    }

    @Override
    public List<Video> queryVideoList(Integer orderNo, String companyCode) {
        Video condition = new Video();
        condition.setOrderNo(orderNo);
        condition.setCompanyCode(companyCode);
        return videoDAO.selectList(condition);
    }

    @Override
    public Video getVideo(String code) {
        Video data = null;
        if (StringUtils.isNotBlank(code)) {
            Video condition = new Video();
            condition.setCode(code);
            data = videoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "不存在该视频");
            }
        }
        return data;
    }

}
