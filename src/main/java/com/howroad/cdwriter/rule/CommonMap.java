package com.howroad.cdwriter.rule;

import com.howroad.cdwriter.conf.PageConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

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
        Validate.notEmpty(map, "CommonMap初始化异常！");
        //变量加载
        map.put("appNo", PageConfig.appNo);
        map.put("appNoUpper", StringUtils.upperCase(PageConfig.appNo));
        map.put("now",getNow());
    }
    private static String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
