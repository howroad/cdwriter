package com.howroad.cdwriter.service.impl;

import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.conf.SystemConfig;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.service.IIOService;
import com.howroad.cdwriter.util.LineUtil;
import com.howroad.cdwriter.util.ValidateUtil;
import org.apache.commons.lang3.Validate;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <p>Title: IOServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-12 16:46
 */
public class IOServiceImpl implements IIOService {

    @Override
    public List<String> readToLine(InputStream ins) {
        List<String> lineList = new ArrayList<String>();
        BufferedReader in = null ;
        try {
            in = new BufferedReader(new InputStreamReader(ins, SystemConfig.INPUT_CODE));
            String line = null;
            while((line = in.readLine()) != null) {
                lineList.add(new String(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return lineList;
    }
    @Override
    public List<String> readToLine(File file) {
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return readToLine(ins);
    }

    @Override
    public List<String> readToLine(String path) {
        File file = new File(path);
        return readToLine(file);
    }



    @Override
    public void write(File file, List<String> lineList) {
        ValidateUtil.notEmpty(lineList);
        PrintWriter out = null;
        try {
            File father = file.getParentFile();
            if(!father.exists()) {
                father.mkdirs();
            }
            out = new PrintWriter(file, SystemConfig.WRITE_CODE);
            for (String string : lineList) {
                out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

    @Override
    public void write(String path, List<String> lineList) {
        File file = new File(path);
        write(file,lineList);
    }

    @Override
    public void write(File file, String line) {
        Validate.notNull(line);
        PrintWriter out = null;
        try {
            File father = file.getParentFile();
            if(!father.exists()) {
                father.mkdirs();
            }
            out = new PrintWriter(file, SystemConfig.WRITE_CODE);
            out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

    @Override
    public void write(String path, String line) {
        File file = new File(path);
        write(file,line);
    }

    @Override
    public void writeFileByTemplet(File templet, File outFile, Table table) {
        List<String> lineList = readToLine(templet);
        List<String> resultList = LineUtil.buildNewLine(lineList, table);
        write(outFile,resultList);
    }


    @Override
    public List<JarEntry> readAllTempletJarEntry() {
        List<JarEntry> list = new ArrayList<JarEntry>();
        try {
            //获得jar包路径
            JarFile jFile = new JarFile(System.getProperty("java.class.path"));
            Enumeration<JarEntry> jarEntrys = jFile.entries();
            while (jarEntrys.hasMoreElements()) {
                JarEntry entry = jarEntrys.nextElement();
                String name = entry.getName();
                if(name.startsWith(PathConfig.TEMPLET_DIR)) {
                    list.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public JarEntry getJarEntry(String entryName) {
        try {
            //获得jar包路径
            JarFile jFile = new JarFile(System.getProperty("java.class.path"));
            Enumeration<JarEntry> jarEntrys = jFile.entries();
            while (jarEntrys.hasMoreElements()) {
                JarEntry entry = jarEntrys.nextElement();
                String name = entry.getName();
                if(name.contains(".templet"))
                    if(name.endsWith(entryName)) {
                        return entry;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void writeTempletByEntry(Table table, String path, JarEntry jarEntry, File outPath) {

    }

    @Override
    public void writeAllTempletFromJar(Table table, String path) {

    }

    @Override
    public int getLastKeyLineNum(List<String> lineList, String key) {
        return 0;
    }

    @Override
    public void reWriteFileByList(File file, List<String> list, Table table, int fileType) {

    }

    @Override
    public void writeCommonFileByTemplet(Table table) {

    }



    @Override
    public void writeFileByTemplet(InputStream ins, File outFile, Table table) {

    }

    @Override
    public void writeAllTemplet(Table table, String path, String templetDir) {

    }

    @Override
    public void writeDataFile(Table table) {

    }

    @Override
    public void writeDataFile(Table table, String sql, String[] primaryColUpKeys, String filName) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void clearDir(File dir) {

    }

    @Override
    public void reBuildCommonFile() {

    }

    @Override
    public void clearAndRebuild() {

    }
}
