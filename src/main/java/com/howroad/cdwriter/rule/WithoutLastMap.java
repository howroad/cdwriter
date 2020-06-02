package com.howroad.cdwriter.rule;

import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.util.PropertiesUtil;

import java.util.Map;

/**
 * <p>Title: WithoutLastMap.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 14:34
 */
public class WithoutLastMap {
    public static Map<String,String> map;
    public static void init(){
        if(map == null){
            map = PropertiesUtil.readJarProperties(PathConfig.WITHOUT_MAP_PATH);
        }
    }
}
