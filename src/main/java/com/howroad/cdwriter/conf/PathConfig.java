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

    /** 工程模版所在目录 */
    public static String TEMPLET_DIR;

    /** 工程模版文件名映射文件路径 */
    public static String NAME_MAPING_PATH;

    /** 替换规则文件路径(普通替换) */
    public static String COMMON_MAP_PATH;

    /** /替换规则文件路径(除最后一行外替换) */
    public static String WITHOUT_MAP_PATH;

    /** 工程保存的配置文件路径 */
    public static String SAVE_CONFIG_PATH;

    /** 工程保存的新增字段的模版 */
    public static String ADD_COLUMN_TEMPLET;

    /** 工程保存的修改字段的模版 */
    public static String MODIFY_COLUMN_TEMPLET;

    public static String OUT_CODE_DIR(){
        return PageConfig.WORK_SPACE + "/out";
    }
    public static String CUST_TEMPLET_DIR(){
        return PageConfig.WORK_SPACE + "/templet";
    }
    public static String EXCEL_PATH(){
        return PageConfig.WORK_SPACE + "/1.xlsx";
    }

    public static String OUT_SQL_DIR() {
        return OUT_CODE_DIR() + "/sqls/";
    }
    public static String OUT_SQL_PATH(Table table){
        return OUT_CODE_DIR() + "/sqls/" + table.getTableName() + ".SQL";
    }
    public static String OUT_SQL_PATH_CUST(Table table){
        return OUT_CODE_DIR() + "/sqls/cust/" + table.getTableName() + ".SQL";
    }
    public static String ADD_COLUMN_PATH(Table table){
        return OUT_CODE_DIR() + "/db/patch/" + table.getTableName() + ".SQL";
    }
    public static String MODIFY_COLUMN_PATH(Table table){
        return OUT_CODE_DIR() + "/db/patch/" + table.getTableName() + "2.SQL";
    }
}
