package com.howroad.cdwriter.service;

import com.howroad.cdwriter.model.Table;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: IIOService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 14:04
 */
public interface IIoService {

    /**
     * 获得一个字符串集合
     * @param ins 输入流
     * @param code 编码
     * @return 获得一个字符串集合
     */
    List<String> readToLine(InputStream ins,String code);

    /**
     * 获得一个字符串集合
     * @param file 文件
     * @return 获得一个字符串集合
     */
    List<String> readToLine(File file);

    /**
     * 获得一个目录下的所有模版文件（.templet结尾的）
     * @param dir 目录
     * @return 模版文件列表
     */
    List<File> getAllFile(File dir);

    /**
     * 获得本项目jar包内的所有模版
     * @return 文件名和输出流的映射Map，文件名在配置文件中
     */
    Map<String,InputStream> getAllJarTemplet();

    /**
     * 根据模版路径获得输入流，Jar包和非Jar包的时候均可以使用
     * @param templetPath 模版路径
     * @return 输入流
     */
    InputStream getTemplet(String templetPath);

    /**
     * 将字符串集合写入文件，编码是SystemConfig配置的WriteCode
     * @param file 文件
     * @param lineList 字符串集合
     */
    void write(File file, List<String> lineList);

    /**
     *  将字符串集合写入文件
     * @param file 文件
     * @param lineList 字符串集合
     * @param code 编码
     */
    void write(File file, List<String> lineList, String code);

    /**
     * 将字符串集合写入文件
     * @param path 文件路径
     * @param lineList 字符串集合
     */
    void write(String path, List<String> lineList);

    /**
     * 将表信息根据输入流模版写入文件
     * @param ins 输入流模版
     * @param outFile 输出文件
     * @param table 表
     */
    void writeFileByTemplet(InputStream ins,File outFile, Table table);

    /**
     * 将表信息按照某个目录下的所有模版输出成文件
     * @param table 表信息
     * @param outDir 输出目录
     * @param templetDir 模版目录
     */
    void writeAllFileByTemplet(Table table,String outDir,String templetDir);

    /**
     * 将表信息按照Jar包里面的所有模版信息写成文件（输出到配置的输出目录）
     * @param table 表
     */
    void writeAllFileByJarTemplet(Table table);

    /**
     * 将表的插入信息写成insert语句并放入输出目录
     * @param table 表
     */
    void writeDataFile(Table table);

    /**
     * 将表的插入信息写成insert语句并放入输出目录
     * @param table 表信息
     * @param sql 自定义的Sql
     * @param primaryColUpKeys 用于判断可重复执行的主键
     */
    void writeDataFile(Table table, String sql, String[] primaryColUpKeys);

    /**
     * 清理输出目录
     */
    void clear();

    /**
     * 清理目录
     * @param dir 需要清理的目录
     */
    void clearDir(File dir);

    /**
     * 清理目录根据文件名
     * @param dir 目录
     * @param reg 需要清理的文件名正则
     * @param withoutReg 不需要清理的文件名正则
     */
    void clearWithReg(File dir, String[] reg, String[] withoutReg);

    /**
     * 将Map映射信息写到csv文件
     * @param file 文件
     * @param map 映射信息，主要是根据javaBean和表信息生成输出的时候 用来记录字段相似度的
     */
    void writeCsv(File file, Map<String, Map.Entry<String,Integer>> map);
}
