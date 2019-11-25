package com.howroad.cdwriter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title: PropertiesUtil.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 10:12
 */
public class PropertiesUtil {
    public static Map<String,String> readJarProperties(String path){
        Properties pro = new Properties();
        InputStream in = null;
        try {
            in = Class.class.getResourceAsStream(path);
            pro.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String,String> resultMap = new HashMap<>();
        pro.forEach((e,f) ->{
            resultMap.put(String.valueOf(e),String.valueOf(f));
        });
        return resultMap;
    }

    public static Map<String,String> readJarPropertiesUpperCase(String path){
        Properties pro = new Properties();
        InputStream in = null;
        try {
            in = Class.class.getResourceAsStream(path);
            pro.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String,String> resultMap = new HashMap<>();
        pro.forEach((e,f) ->{
            resultMap.put(String.valueOf(e).toUpperCase(),String.valueOf(f));
        });
        return resultMap;
    }

    public static Map<String,String> readPropertiesUpperCase(String path){
        Properties pro = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(new File(path));
            pro.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String,String> resultMap = new HashMap<>();
        pro.forEach((e,f) ->{
            resultMap.put(String.valueOf(e).toUpperCase(),String.valueOf(f));
        });
        return resultMap;
    }
}
