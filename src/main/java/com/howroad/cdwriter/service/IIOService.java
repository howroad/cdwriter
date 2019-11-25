package com.howroad.cdwriter.service;

import com.howroad.cdwriter.model.Table;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * <p>Title: IIOService.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 14:04
 */
public interface IIOService {

    List<String> readToLine(InputStream ins);
    List<String> readToLine(File file);
    List<String> readToLine(String path);

    void write(File file, List<String> lineList);
    void write(String path, List<String> lineList);
    void write(File file, String line);
    void write(String path, String line);

    void writeFileByTemplet(File templet,File outFile, Table table);
    void writeFileByTemplet(InputStream ins,File outFile, Table table);

    List<File> getAllFile(File dir);
    Map<String,InputStream> getAllJarTemplet();
    void writeAllFileByTemplet(Table table,String outDir,String templetDir);
    void writeAllFileByJarTemplet(Table table);

    void writeDataFile(Table table);
    void writeDataFile(Table table, String sql, String[] primaryColUpKeys);

    void clear();
    void clearDir(File dir);
    void clearWithReg(File dir, String[] reg, String[] withoutReg);

}
