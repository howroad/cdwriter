package com.howroad.cdwriter.rule;

import com.howroad.cdwriter.conf.PageConfig;
import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.util.PropertiesUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>Title: CommonMap.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 14:33
 */
public class CommonMap {
    public static Map<String,String> map;
    public static void init(){
        if(map == null){
            //常量加载
            map = PropertiesUtil.readJarPropertiesUpperCase(PathConfig.COMMON_MAP_PATH);
            //变量加载
            map.put("appNo", PageConfig.appNo);
            map.put("appNoUpper", PageConfig.appNo.toUpperCase());
            map.put("now",getNow());
        }
    }
    private static String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //return sdf.format(new Date());
        return "2019-11-25 15:49:22";
    }
}
