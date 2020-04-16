package com.howroad.cdwriter.conf;

/**
 * <p>Title: PageConfig.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 14:25
 */
public class PageConfig {

    /** 数据库URL */
    public static String URL;
    /** 数据库用户 */
    public static String USER;
    /** 密码 */
    public static String PASSWORD;
    /** 模块前缀 */
    public static String appNo;
    /** 从数据库读取的表名 */
    public static String[] tablesFromDB;
    /** 导入的文件名 */
    public static String[] modelFiles;
    /** 序列规则 */
    public static Boolean SEQ_ON_LAST;

    /** 输出工作空间 */
    public static String WORK_SPACE;

    public static void init(){}
}
