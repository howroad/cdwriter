package com.howroad.cdwriter.service.impl;

import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.conf.SystemConfig;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.rule.FileNameMap;
import com.howroad.cdwriter.service.Container;
import com.howroad.cdwriter.service.IIOService;
import com.howroad.cdwriter.util.DBUtil;
import com.howroad.cdwriter.util.LineUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import java.io.*;
import java.util.*;

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
        Validate.notNull(lineList);
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
    public void writeFileByTemplet(InputStream ins, File outFile, Table table) {
        List<String> lineList = readToLine(ins);
        List<String> resultList = LineUtil.buildNewLine(lineList, table);
        write(outFile,resultList);
    }

    @Override
    public List<File> getAllFile(File file) {
        List<File> result = new ArrayList<>();
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                if(file1.isDirectory()){
                    result.addAll(getAllFile(file1));
                }else{
                    if(file1.getName().endsWith(SystemConfig.SUFFIX)){
                        result.add(file1);
                    }
                }
            }
        }else{
            result.add(file);
        }
        return result;
    }

    @Override
    public Map<String,InputStream> getAllJarTemplet() {
        Set<String> names = FileNameMap.localMap.keySet();
        Map<String,InputStream> result = new HashMap<>();
        for (String name : names) {
            InputStream stream = Class.class.getResourceAsStream(PathConfig.TEMPLET_DIR + "/" + name);
            result.put(name, stream);
        }
        return result;
    }


    @Override
    public void writeAllFileByTemplet(Table table, String outDir, String templetDir) {
        File outDirFile = new File(outDir);
        outDirFile.mkdirs();
        File tmpletDirFile = new File(templetDir);
        File[] files = tmpletDirFile.listFiles(file -> file.isDirectory() || file.getName().endsWith(SystemConfig.SUFFIX));
        if(files == null) return;
        for (File file : files) {
            if(file.isFile()){
                List<String> lineList = readToLine(file);
                List<String> newLine = LineUtil.buildNewLine(lineList, table);
                String fileName = LineUtil.builCustdName(file.getName(), table);
                write(outDir + "/" + fileName, newLine);
            }else{
                writeAllFileByTemplet(table, outDir + "/" + file.getName(), templetDir + "/" + file.getName());
            }
        }

    }

    @Override
    public void writeAllFileByJarTemplet(Table table) {
        File outDirFile = new File(PathConfig.OUT_CODE_DIR());
        outDirFile.mkdirs();
        Map<String, InputStream> allJarTemplet = getAllJarTemplet();
        Set<String> keySet = allJarTemplet.keySet();
        for (String key : keySet) {
            String fileName = LineUtil.buildName(key, table);
            List<String> lineList = readToLine(allJarTemplet.get(key));
            List<String> newLine = LineUtil.buildNewLine(lineList, table);
            write(PathConfig.OUT_CODE_DIR() + "/" + fileName, newLine);
        }
    }

    @Override
    public void writeDataFile(Table table) {
        List<List<Object>> listList = DBUtil.query("SELECT * FROM " + table.getTableName() + " WHERE ROWNUM <= 200 ORDER BY 1");
        List<String> line = Container.databaseService.dataToLine(table, listList);
        write(new File(PathConfig.OUT_SQL_PATH(table)), line);
    }

    @Override
    public void writeDataFile(Table table, String sql, String[] primaryColUpKeys) {
        List<String> line = Container.databaseService.custDataToLine(table, sql, primaryColUpKeys);
        write(new File(PathConfig.OUT_SQL_PATH_CUST(table)), line);
    }

    @Override
    public void clear() {
        Container.ioService.clearWithReg(new File(PathConfig.OUT_CODE_DIR()), new String[]{".+\\.+(java|xml|Table|PDC)"}, new String[]{});
    }

    @Override
    public void clearDir(File dir) {
        if(dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if(file.isFile()) {
                    if(".gitkeep".equals(file.getName())){
                        continue;
                    }
                    if(file.getName().endsWith(".xlsx")){
                        continue;
                    }
                    if(file.getName().endsWith(".jar")){
                        continue;
                    }
                    if(file.getName().toUpperCase().endsWith(".CMD")){
                        continue;
                    }
                    file.delete();
                }else if(file.isDirectory()) {
                    clearDir(file);
                    file.delete();
                }
            }
        }
    }

    @Override
    public void clearWithReg(File dir, String[] regs, String[] withoutRegs) {
        Validate.notEmpty(regs);
        Validate.notNull(withoutRegs);
        if(dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            a:for (File file : files) {
                String fileName = file.getName();
                if(file.isFile()) {
                    b:
                    for (String withoutReg : withoutRegs) {
                        if(fileName.matches(withoutReg)){
                            continue a;
                        }
                    }
                    c:
                    for (String reg : regs) {
                        if(fileName.matches(reg)){
                            file.delete();
                            continue a;
                        }
                    }
                }else if(file.isDirectory()) {
                    clearDir(file);
                    if(ArrayUtils.isEmpty(file.listFiles())){
                        file.delete();
                    }
                }
            }
        }
    }
}
