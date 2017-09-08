/**
 * @Title ISYSConfigBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:52 
 * @version V1.0   
 */
package com.std.gym.bo;

import java.util.List;
import java.util.Map;

import com.std.gym.bo.base.IPaginableBO;
import com.std.gym.domain.SYSConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:52 
 * @history:
 */
public interface ISYSConfigBO extends IPaginableBO<SYSConfig> {
    public int refreshSYSConfig(SYSConfig data);

    public SYSConfig getConfig(Long id);

    /**
     * 根据key获取value
     * @param ckey
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2017年4月23日 下午8:08:12 xieyj
     * @history:
     */
    public SYSConfig getConfigValue(String ckey, String companyCode,
            String systemCode);

    public List<SYSConfig> querySYSConfigList(String type);

    Map<String, String> querySYSConfigMap(String type);
}
