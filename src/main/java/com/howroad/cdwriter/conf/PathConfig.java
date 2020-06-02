package com.howroad.cdwriter.conf;

import com.howroad.cdwriter.model.Table;

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

    /**
     * 工程模版所在目录
     */
    public static String TEMPLET_DIR;

    /**
     * 工程模版文件名映射文件路径
     */
    public static String NAME_MAPING_PATH;

    /**
     * 替换规则文件路径(普通替换)
     */
    public static String COMMON_MAP_PATH;

    /**
     * 替换规则文件路径(除最后一行外替换)
     */
    public static String WITHOUT_MAP_PATH;

    /**
     * 工程保存的配置文件路径
     */
    public static String SAVE_CONFIG_PATH;

    /**
     * 本地保存的自定义配置文件路径
     */
    public static String SAVE_CUST_CONFIG_PATH;

    /**
     * 工程保存的新增字段的模版
     */
    public static String ADD_COLUMN_TEMPLET;

    /**
     * 工程保存的修改字段的模版
     */
    public static String MODIFY_COLUMN_TEMPLET;

    /**
     * 版本编号文件
     */
    public static String VERSION_FILE_PATH;

    public static String configPath() {
        return String.format("%1$ssystem_%2$s.properties", SAVE_CONFIG_PATH, VersionConfig.cdWriterVersion);
    }

    public static String csvPath() {
        return PageConfig.WORK_SPACE;
    }

    public static String outCodeDir() {
        return PageConfig.WORK_SPACE + "/out";
    }

    public static String custTempletDir() {
        return PageConfig.WORK_SPACE + "/templet";
    }

    public static String excelPath() {
        return PageConfig.WORK_SPACE + "/1.xlsx";
    }

    public static String outSqlDir() {
        return outCodeDir() + "/sqls/";
    }

    public static String outSqlPath(Table table) {
        return outCodeDir() + "/sqls/" + table.getTableName() + ".SQL";
    }

    public static String outSqlPathCust(Table table) {
        return outCodeDir() + "/sqls/cust/" + table.getTableName() + ".SQL";
    }

    public static String addColumnPath(Table table) {
        return outCodeDir() + "/db/patch/" + table.getTableName() + ".TAB";
    }

    public static String modifyColumnPath(Table table) {
        return outCodeDir() + "/db/patch/" + table.getTableName() + "2.TAB";
    }

    public static String inCodeDir() {
        return PageConfig.WORK_SPACE + "/in/";
    }
}
