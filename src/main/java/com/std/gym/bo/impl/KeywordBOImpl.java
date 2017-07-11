/**
 * @Title KeywordBOImpl.java 
 * @Package com.std.forum.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午5:37:39 
 * @version V1.0   
 */
package com.std.gym.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IKeywordBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IKeywordDAO;
import com.std.gym.domain.Keyword;
import com.std.gym.enums.EReaction;
import com.std.gym.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 下午5:37:39 
 * @history:
 */
@Component
public class KeywordBOImpl extends PaginableBOImpl<Keyword> implements
        IKeywordBO {

    @Autowired
    private IKeywordDAO keywordDAO;

    /** 
     * @see com.std.gym.bo.IKeywordBO#saveKeyword(com.std.gym.domain.Keyword)
     */
    @Override
    public void saveKeyword(Keyword data) {
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            keywordDAO.insert(data);
        }
    }

    /** 
     * @see com.std.gym.bo.IKeywordBO#refreshKeyword(com.std.gym.domain.Keyword)
     */
    @Override
    public int refreshKeyword(Keyword data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            count = keywordDAO.update(data);
        }
        return count;
    }

    /** 
     * @see com.std.gym.bo.IKeywordBO#getKeyword(java.lang.String)
     */
    @Override
    public Keyword getKeyword(String code) {
        Keyword result = null;
        if (StringUtils.isNotBlank(code)) {
            Keyword condition = new Keyword();
            condition.setCode(code);
            result = keywordDAO.select(condition);
        }
        return result;
    }

    /** 
     * @see com.std.gym.bo.IKeywordBO#queryKeywordList(com.std.gym.domain.Keyword)
     */
    @Override
    public List<Keyword> queryKeywordList(Keyword condition) {
        return keywordDAO.selectList(condition);
    }

    /** 
     * @see com.std.gym.bo.IKeywordBO#removeKeyword(java.lang.String)
     */
    @Override
    public int removeKeyword(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Keyword condition = new Keyword();
            condition.setCode(code);
            count = keywordDAO.delete(condition);
        }
        return count;
    }

    @Override
    public Boolean isKeywordExist(String code) {
        Boolean count = false;
        if (StringUtils.isNotBlank(code)) {
            Keyword condition = new Keyword();
            condition.setCode(code);
            if (null != keywordDAO.select(condition)) {
                count = true;
            }
        }
        return count;
    }

    @Override
    public String replaceKeyword(String content, String word) {
        String replacement = "";
        for (int i = 0; i < word.length(); i++) {
            replacement += "*";
        }
        content = content.replaceAll(word, replacement);
        return content;
    }

    @Override
    public EReaction checkContent(String content) {
        if (StringUtils.isBlank(content)) {
            throw new BizException("xn000000", "内容不能为空");
        }
        List<Keyword> resultList = new ArrayList<Keyword>();
        // 针对所有
        Keyword condition = new Keyword();
        condition.setWeightStart(0.5);
        List<Keyword> allList = keywordDAO.selectList(condition);
        for (Keyword keyword : allList) {
            if (content.indexOf(keyword.getWord()) >= 0) {
                resultList.add(keyword);
            }
        }
        if (CollectionUtils.isNotEmpty(resultList)) {
            return EReaction.REFUSE;
        } else {
            return EReaction.NORMAL;
        }
    }
}
