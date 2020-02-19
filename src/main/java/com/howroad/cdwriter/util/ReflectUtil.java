package com.howroad.cdwriter.util;


import com.howroad.log.PanelLog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

/**
 * <p>Title: ReflectUtil.java</p>
 * <p>Description: 反射工具类</p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 09:48
 */
public class ReflectUtil {

    /**
     * 从Map中设置静态变量
     *
     * @param field
     * @param paramMap
     */
    public static void setStaticParam(Field field, Map<String, String> paramMap) {
        String fieldName = field.getName().toUpperCase();
        if ("NULL".equals(fieldName)) {
            throw new RuntimeException("非法的Key：NULL！");
        }
        String value = paramMap.get(fieldName);
        if (value == null) {
            System.out.println(field.getDeclaringClass().getName() + " : " + fieldName + ":未设置！");
            PanelLog.log(field.getDeclaringClass().getName() + " : " + fieldName + ":未设置！");
            return;
        }
        try {
            if (field.getType() == String.class) {
                field.set(null, value);
            } else if (field.getType() == Integer.class) {
                field.set(null, Integer.valueOf(value));
            } else if (field.getType() == Boolean.class) {
                field.set(null, Boolean.valueOf(value));
            } else if (field.getType() == Double.class) {
                field.set(null, Double.valueOf(value));
            } else if (field.getType().isArray() && field.getType().getComponentType() == String.class) {
                String[] strArr = value.split(",");
                field.set(null, strArr);
            } else if (field.getType().isArray() && field.getType().getComponentType() == Integer.class) {
                String[] strArr = value.split(",");
                Integer[] intArr = new Integer[strArr.length];
                field.set(null, intArr);
                for (int i = 0; i < intArr.length; i++) {
                    Integer intValue = Integer.valueOf(strArr[i]);
                    intArr[i] = intValue;
                }
            } else {
                throw new RuntimeException("意外的类型：" + field.getType());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获得所有的静态变量
     *
     * @param clazz
     * @return
     */
    public static Field[] getStaticFields(Class clazz) {
        Field[] fields = Arrays.stream(clazz.getFields())
                .filter(e -> Modifier.isStatic(e.getModifiers()) && !Modifier.isFinal(e.getModifiers()))
                .toArray(Field[]::new);
        return fields;
    }

}