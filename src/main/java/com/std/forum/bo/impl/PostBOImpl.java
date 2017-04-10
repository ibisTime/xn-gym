/**
 * @Title PostBOImp.java 
 * @Package com.std.forum.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午4:24:47 
 * @version V1.0   
 */
package com.std.forum.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.IPostBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.OrderNoGenerater;
import com.std.forum.dao.IPostDAO;
import com.std.forum.domain.Post;
import com.std.forum.enums.EBoolean;
import com.std.forum.enums.ELocation;
import com.std.forum.enums.EPostStatus;
import com.std.forum.enums.EPrefixCode;
import com.std.forum.exception.BizException;

/** 
 * 帖子BO
 * @author: xieyj 
 * @since: 2016年8月29日 下午4:24:47 
 * @history:
 */
@Component
public class PostBOImpl extends PaginableBOImpl<Post> implements IPostBO {

    @Autowired
    private IPostDAO postDAO;

    @Override
    public String savePost(String title, String content, String pic,
            String plateCode, String publisher, String status) {
        Post data = new Post();
        String code = OrderNoGenerater.generate(EPrefixCode.POST.getCode());
        data.setCode(code);
        data.setTitle(title);
        data.setContent(content);
        data.setPic(pic);
        data.setPlateCode(plateCode);
        data.setPublisher(publisher);
        data.setStatus(status);
        data.setPublishDatetime(new Date());
        data.setIsLock(EBoolean.NO.getCode());
        data.setLocation(ELocation.ALL.getCode());
        data.setOrderNo(0);
        data.setSumComment(0);
        data.setSumLike(0);
        data.setSumRead(0);
        data.setSumReward(0);
        postDAO.insert(data);
        return code;
    }

    @Override
    public void refreshPost(String code, String title, String content,
            String pic, String plateCode, String publisher, String status) {
        Post data = new Post();
        data.setCode(code);
        data.setTitle(title);
        data.setContent(content);
        data.setPic(pic);
        data.setPlateCode(plateCode);
        data.setStatus(status);
        data.setPublisher(publisher);
        data.setPublishDatetime(new Date());
        data.setIsLock(EBoolean.NO.getCode());
        data.setLocation(ELocation.ALL.getCode());
        data.setSumComment(0);
        data.setSumLike(0);
        data.setSumRead(0);
        data.setSumReward(0);
        postDAO.update(data);
    }

    @Override
    public int removePost(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            count = postDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPostApprove(String code, String approver,
            String approveResult, String approveNote) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            data.setStatus(EPostStatus.APPROVE_NO.getCode());
            if (EBoolean.YES.getCode().equals(approveResult)) {
                data.setStatus(EPostStatus.APPROVE_YES.getCode());
            }
            count = postDAO.updateApprove(data);
        }
        return count;
    }

    @Override
    public int refreshPostReturn(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setStatus(EPostStatus.APPROVE_YES.getCode());
            count = postDAO.updateStatus(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#getPost(java.lang.String)
     */
    @Override
    public Post getPost(String code) {
        Post result = null;
        if (StringUtils.isNotBlank(code)) {
            Post condition = new Post();
            condition.setCode(code);
            result = postDAO.select(condition);
            if (result == null) {
                throw new BizException("xn000000", "帖子编号不存在");
            }
        }
        return result;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#queryPostList(com.std.forum.domain.Post)
     */
    @Override
    public List<Post> queryPostList(Post condition) {
        return postDAO.selectList(condition);
    }

    @Override
    public long getPostNum(Post condition) {
        return postDAO.selectPostNum(condition);
    }

    @Override
    public long getPostNum(String plateCode, String status) {
        Post condition = new Post();
        condition.setPlateCode(plateCode);
        condition.setStatus(status);
        return postDAO.selectPostNum(condition);
    }

    @Override
    public int refreshPostLocation(String code, String location,
            Integer orderNo, String updater) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setLocation(location);
            data.setOrderNo(orderNo);
            data.setApprover(updater);
            data.setApproveDatetime(new Date());
            count = postDAO.updateLocation(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#refreshPostHeadlines(java.lang.String)
     */
    @Override
    public int refreshPostLock(String code, boolean flag) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setIsLock(EBoolean.YES.getCode());
            if (flag == true) {
                data.setIsLock(EBoolean.NO.getCode());
            }
            count = postDAO.updateLock(data);
        }
        return count;
    }

    @Override
    public int refreshPostPlate(String code, String plateCode) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setPlateCode(plateCode);
            count = postDAO.updatePlate(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#refreshPostReport(java.lang.String, java.lang.String)
     */
    @Override
    public int refreshPostReport(String code, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setStatus(EPostStatus.toReportAPPROVE.getCode());
            data.setRemark(remark);
            count = postDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public int refreshPostSumComment(String code, Integer sumComment) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setSumComment(sumComment);
            count = postDAO.updateSumComment(data);
        }
        return count;
    }

    @Override
    public int refreshPostSumLike(String code, Integer sumLike) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setSumLike(sumLike);
            count = postDAO.updateSumLike(data);
        }
        return count;
    }

    @Override
    public int refreshPostSumRead(String code, Integer sumRead) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setSumRead(sumRead);
            count = postDAO.updateSumRead(data);
        }
        return count;
    }

    @Override
    public int refreshPostSumReward(String code, Integer sumReward) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setSumReward(sumReward);
            count = postDAO.updateSumReward(data);
        }
        return count;
    }

    @Override
    public Long getMyPostCount(String userId, String status) {
        Post condition = new Post();
        condition.setPublisher(userId);
        condition.setStatus(status);
        return postDAO.getMyPostCount(condition);
    }

    @Override
    public List<Post> selectSCList(Post condition) {
        return postDAO.selectSCList(condition);
    }

    @Override
    public List<Post> queryPostSCList(Post condition, int start, int limit) {
        return postDAO.selectSCList(condition, start, limit);
    }

    @Override
    public long getPostLocation(String location) {
        if (StringUtils.isNotBlank(location)) {
            String[] desc = location.split(",");
            for (int i = 0; i < desc.length; i++) {
                location = desc[i] + "%";
            }
        }
        Post condition = new Post();
        condition.setLocation(location);
        return postDAO.selectPostNum(condition);
    }

    @Override
    public List<Post> selectTDList(Post condition) {
        return postDAO.selectTDList(condition);
    }

    @Override
    public List<Post> queryTDPostList(Post condition, int start, int limit) {
        return postDAO.queryTDPostList(condition, start, limit);
    }

    @Override
    public List<Post> queryPostList(String splateCode) {
        Post condition = new Post();
        condition.setPlateCode(splateCode);
        return postDAO.selectList(condition);
    }
}
