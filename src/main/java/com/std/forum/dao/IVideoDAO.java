package com.std.forum.dao;

import com.std.forum.dao.base.IBaseDAO;
import com.std.forum.domain.Video;

//dao层 
public interface IVideoDAO extends IBaseDAO<Video> {
    String NAMESPACE = IVideoDAO.class.getName().concat(".");

    public int update(Video data);

    public int updateUpVideo(Video data);

    public int updateDownVideo(Video data);
}
