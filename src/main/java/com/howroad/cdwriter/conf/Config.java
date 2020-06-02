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

    public static final int JAR = 0;
    public static final int PATH = 1;

    public static void initClass() {
        //初始化PathConfig
        setParam(PathConfig.class, PathConfig.PROPERTIES_PATH, JAR);
        //初始化version
        setParam(VersionConfig.class, PathConfig.VERSION_FILE_PATH, JAR);
        //写入PageConfig--暂时使用properties文件写入
        setParam(PageConfig.class, PathConfig.configPath(), PATH);


        //初始化配置文件CommonMap
        setParam(CommonMap.class, PathConfig.COMMON_MAP_PATH, JAR);
        //根据用户设置初始化CommonMap
        setParam(CommonMap.class, PathConfig.SAVE_CUST_CONFIG_PATH, PATH);

        initClass(CommonMap.class);
        initClass(WithoutLastMap.class);
        initClass(FileNameMap.class);

        log(PathConfig.class);
        log(PageConfig.class);
        log(CommonMap.class);
        log(WithoutLastMap.class);
        log(FileNameMap.class);
    }


    public static void setParam(Class clazz, String path, Integer source) {
        Map<String, String> result;
        if (JAR == source) {
            result = PropertiesUtil.readJarProperties(path);
        } else if (PATH == source) {
            result = PropertiesUtil.readPageConfig(path);
        } else {
            throw new RuntimeException();
        }
        Field[] fields = ReflectUtil.getStaticFields(clazz);
        for (Field field : fields) {
            ReflectUtil.setStaticParam(field, result);
        }

    }

    public static void initClass(Class... classes) {
        for (Class clazz : classes) {
            try {
                Method method = clazz.getMethod("init");
                if (method != null) {
                    method.invoke(null);
                }
            } catch (NoSuchMethodException e) {
                PanelLog.log(e.getClass().getSimpleName() + " : " + e.getMessage());
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void log(Class clazz) {
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
