package com.howroad.cdwriter.rule;

import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: FileNameMapping.java</p>
 * <p>Description: 名称映射规则文件 </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 11:30
 */
public class FileNameMap {

    /** 自定义模版名称映射文件 */
    public static Map<String, String> custMap;
    /** 内置模版文件 */
    public static Map<String, String> localMap;

    public static void init() {
        localMap = PropertiesUtil.readJarProperties(PathConfig.NAME_MAPING_PATH);
        if (custMap != null) {
            HashMap<String, String> map1 = new HashMap<>(custMap);
            map1.putAll(localMap);
            custMap = map1;
        }else {
            custMap = localMap;
        }
    }

    public static String getCustName(String key) {
        if (custMap == null) {
            init();
        }
        String result = custMap.get(key.toUpperCase());
        if (StringUtils.isBlank(result)) {
            result = localMap.get(key.toUpperCase());
        }
        if (StringUtils.isBlank(result)) {
            result = key.replaceAll("\\.TEMPLET|\\.templet", "");
        }
        return result;

    }

    public static String getLocalName(String key) {
        if (localMap == null) {
            localMap = PropertiesUtil.readJarProperties(PathConfig.NAME_MAPING_PATH);
        }
        String result = null;
        Set<String> keySet = localMap.keySet();
        for (String k : keySet) {
            if (("db/" + key).equals(k) || key.equals(k)) {
                result = localMap.get(k);
                break;
            }
        }

        if (StringUtils.isBlank(result)) {
            result = key.replaceAll("\\.TEMPLET|\\.templet", "");
        }
        return result;
    }


}
