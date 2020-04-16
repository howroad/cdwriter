package com.howroad.cdwriter.service;

import com.howroad.frame.jframe.ShowFrame;

/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * @author luhao
 * @since：2019-09-12 16:32
 */
public interface ICoreService {
    /**
     * 测试连接
     * @return 成功或失败
     */
    boolean testCoonect();

    /**
     * 创建基于Excel的输出信息
     */
    void createFromXls();

    /**
     * 创建基于数据库的输出信息
     */
    void createFromDb();

    /**
     * 清理输出目录
     */
    void clear();

    /**
     * 创建自定义数据脚本
     * @param tbNamesStr 表名
     * @param sqlsStr 脚本的Sql
     * @param pkNamesStr 主键，用于可重复执行脚本的判断
     */
    void createCustSql(String tbNamesStr,String sqlsStr,String pkNamesStr);

    /**
     * 创建基于自定义JavaBean和数据库表的映射的输出信息
     */
    void createFromDbAndFile();
}
