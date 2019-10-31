package com.howroad.cdwriter.conf;

/**
 * <p>Title: PathConfig.java</p>
 * <p>Description: 所有的路径配置</p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 11:26
 */
public class PathConfig {

    public final static String PROPERTIES_PATH = "/com/howroad/cdwriter/conf/path.properties";

    // 输出工作空间
    public static String OUT_WORK_SPACE;

    //输出的代码目录
    public static String OUT_CODE_DIR;

    //本地模版所在目录
    public static String TEMPLET_DIR;

    //本地模版文件名映射文件路径
    public static String NAME_MAPING_PATH;

    //外部模版所在文件夹
    public static String CUST_TEMPLET_DIR;

    //替换规则文件路径(普通替换)
    public static String COMMON_MAP_PATH;

    //替换规则文件路径(除最后一行外替换)
    public static String WITHOUT_MAP_PATH;

    //本地保存的配置文件路径
    public static String SAVE_CONFIG_PATH;

}
