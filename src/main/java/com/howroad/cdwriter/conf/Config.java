package com.howroad.cdwriter.conf;

import com.howroad.cdwriter.rule.CommonMap;
import com.howroad.cdwriter.rule.FileNameMap;
import com.howroad.cdwriter.rule.WithoutLastMap;
import com.howroad.cdwriter.util.PropertiesUtil;
import com.howroad.cdwriter.util.ReflectUtil;
import com.howroad.log.PanelLog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>Title: Config.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 16:25
 */
public class Config {
    public static void init(){
        //初始化PathConfig
        initClass(PathConfig.class,PathConfig.PROPERTIES_PATH);
        //写入PageConfig--暂时使用properties文件写入
        initClassFromAbs(PageConfig.class,PathConfig.SAVE_CONFIG_PATH);
        //初始化CommonMap
        initClass(CommonMap.class, PathConfig.COMMON_MAP_PATH);
        //初始化WithoutMapMap
        initClass(WithoutLastMap.class, PathConfig.WITHOUT_MAP_PATH);
        //初始化FileNameMap
        initClassNotUp(FileNameMap.class, PathConfig.NAME_MAPING_PATH);
        //初始化version
        initClass(VersionConfig.class, PathConfig.VERSION_FILE_PATH);

        log(PathConfig.class);
        log(PageConfig.class);
        log(CommonMap.class);
        log(WithoutLastMap.class);
        log(FileNameMap.class);
    }

    public static void initClass(Class clazz, String path){
        try {
            Map<String,String> result = PropertiesUtil.readJarPropertiesUpperCase(path);
            Field[] fields = ReflectUtil.getStaticFields(clazz);
            for (Field field : fields) {
                ReflectUtil.setStaticParam(field,result);
            }
            Method method = clazz.getMethod("init");
            if(method != null){
                method.invoke(null);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchMethodException e) {
            PanelLog.log(e.getClass().getSimpleName() + " : " + e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void initClassNotUp(Class clazz, String path){
        try {
            Map<String,String> result = PropertiesUtil.readJarPropertiesUpperCase(path);
            Field[] fields = ReflectUtil.getStaticFields(clazz);
            for (Field field : fields) {
                ReflectUtil.setStaticParam(field,result);
            }
            Method method = clazz.getMethod("init");
            if(method != null){
                method.invoke(null);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

      public static void initClassFromAbs(Class clazz, String path){
        try {
            Map<String,String> result = PropertiesUtil.readPageConfig(path);
            Field[] fields = ReflectUtil.getStaticFields(clazz);
            for (Field field : fields) {
                ReflectUtil.setStaticParam(field,result);
            }
            Method method = clazz.getMethod("init");
            if(method != null){
                method.invoke(null);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchMethodException e) {
            PanelLog.log(e.getClass().getSimpleName() + " : " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public static void log(Class clazz){
        try {
            PanelLog.log("-----------" + clazz.getName() + "  start-----------");
            Field[] fields = ReflectUtil.getStaticFields(clazz);
            for (Field field : fields) {
                PanelLog.log(field.getName() + ": " + field.get(null));
            }
            PanelLog.log("-----------" + clazz.getName() + "  end-----------");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
