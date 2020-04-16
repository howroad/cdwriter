package com.howroad.cdwriter.service;

import com.howroad.cdwriter.model.Table;
import com.howroad.frame.panel.JoinSqlLine;

import java.io.File;
import java.util.List;

/**
 * <p>Title: ISqlService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 11:49
 */
public interface IDatabaseService {
    /**
     * 创建表连接Sql
     * @param lineList 输入转化的行信息
     * @param templet 模版
     * @param sqlId sqlId
     * @return 输出文件
     */
    File createJoinSql(List<JoinSqlLine> lineList, File templet, String sqlId);

    /**
     * 根据行信息输出多个表
     * @param lineList 输入转化的行信息
     * @return 表列表
     */
    List<Table> getTable(List<JoinSqlLine> lineList);

    /**
     * 根据行信息输出多个表的表名
     * @param lineList 输入转化的行信息
     * @return 表名的列表
     */
    List<String> getTableNames(List<JoinSqlLine> lineList);

    /**
     * 通过sql查询数据
     * @param sql 自定义Sql
     * @return list类型的list
     */
    List<List<Object>> query(String sql);

    /**
     * 获得数据库默认的DDL
     * @param tableName 表名
     * @return ddl语句
     */
    String defaultDdl(String tableName);

    /**
     * 获得数据初始化脚本，自定义主键
     * @param table 表
     * @param sql 自定义sql
     * @param primaryColUpKeys 主键/主键组合
     * @return 行信息
     */
    List<String> custDataToLine(Table table, String sql, String[] primaryColUpKeys);

    /**
     * 将数据库中的数据转换成插入脚本，默认是主键
     * @param table 表
     * @param dataList 数据库表
     * @return 行信息
     */
    List<String> dataToLine(Table table, List<List<Object>> dataList);


}
