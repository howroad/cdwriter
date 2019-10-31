package com.howroad.cdwriter.rule;

import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <p>Title: FileNameMapping.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 11:30
 */
public class FileNameMap {

    public static Map<String,String> map;
    public static Map<String,String> localMap;
    public static void init(){
        localMap = PropertiesUtil.readJarPropertiesUpperCase(PathConfig.NAME_MAPING_PATH);
        map = localMap;
    }
    public static String getCustName(String key){
        if(map == null){
            init();
        }
        String result = map.get(key);
        if(StringUtils.isBlank(result)){
            result = localMap.get(key);
        }
        if(StringUtils.isBlank(result)){
            result = key.replace("TEMPLET","");
        }
        return result;

    }

    public static String getLocalName(String key){
        if(localMap == null){
            localMap = PropertiesUtil.readJarPropertiesUpperCase(PathConfig.NAME_MAPING_PATH);
        }
        String result = localMap.get(key);
        if(StringUtils.isBlank(result)){
            result = key.replace("TEMPLET","");
        }
        return result;
    }


}
