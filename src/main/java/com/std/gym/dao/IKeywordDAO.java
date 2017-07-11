/**
 * @Title IKeywordDAO.java 
 * @Package com.std.forum.dao 
 * @Description 
 * @author xieyj  
 * @date 2016年8月28日 下午9:34:28 
 * @version V1.0   
 */
package com.std.gym.dao;

import com.std.gym.dao.base.IBaseDAO;
import com.std.gym.domain.Keyword;

/** 
 * 站点DAO
 * @author: xieyj 
 * @since: 2016年8月28日 下午9:34:28 
 * @history:
 */
public interface IKeywordDAO extends IBaseDAO<Keyword> {
    String NAMESPACE = IKeywordDAO.class.getName().concat(".");

    /**
     * 更新关键词
     */
    public int update(Keyword data);

}
