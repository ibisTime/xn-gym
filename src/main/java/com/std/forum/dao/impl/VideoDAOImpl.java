package com.std.forum.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.forum.dao.IVideoDAO;
import com.std.forum.dao.base.support.AMybatisTemplate;
import com.std.forum.domain.Video;

@Repository("videoDAOImpl")
public class VideoDAOImpl extends AMybatisTemplate implements IVideoDAO {

    @Override
    public int insert(Video data) {
        return super.insert(NAMESPACE.concat("insert_video"), data);
    }

    @Override
    public int delete(Video data) {
        return super.delete(NAMESPACE.concat("delete_video"), data);
    }

    @Override
    public Video select(Video condition) {
        return super.select(NAMESPACE.concat("select_video"), condition,
            Video.class);
    }

    @Override
    public Long selectTotalCount(Video condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_video_count"),
            condition);
    }

    @Override
    public List<Video> selectList(Video condition) {
        return super.selectList(NAMESPACE.concat("select_video"), condition,
            Video.class);
    }

    @Override
    public List<Video> selectList(Video condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_video"), start, count,
            condition, Video.class);
    }

    @Override
    public int update(Video data) {
        return super.update(NAMESPACE.concat("update_video"), data);
    }

    @Override
    public int updateUpVideo(Video data) {
        return super.update(NAMESPACE.concat("update_video_up"), data);
    }

    @Override
    public int updateDownVideo(Video data) {
        return super.update(NAMESPACE.concat("update_video_down"), data);
    }

}
