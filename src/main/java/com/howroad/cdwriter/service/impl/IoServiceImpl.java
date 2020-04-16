package com.howroad.cdwriter.service.impl;

import com.google.common.io.Files;
import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.conf.SystemConfig;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.rule.FileNameMap;
import com.howroad.cdwriter.service.Container;
import com.howroad.cdwriter.service.IIOService;
import com.howroad.cdwriter.util.DBUtil;
import com.howroad.cdwriter.util.LineUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: IOServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-12 16:46
 */
public class IoServiceImpl implements IIOService {

    @Override
    public List<String> readToLine(InputStream ins, String code) {
        List<String> lineList = new ArrayList<String>();
        BufferedReader in = null ;
        try {
            in = new BufferedReader(new InputStreamReader(ins, code));
            String line = null;
            while((line = in.readLine()) != null) {
                lineList.add(new String(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

        }
        return lineList;
    }
    @Override
    public List<String> readToLine(File file) {
        List<String> strings = null;
        try {
            String code = getCharset(file);
            strings = Files.readLines(file, Charset.forName(code));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return strings;
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
            throw new RuntimeException();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

    @Override
    public void write(File file, List<String> lineList, String code) {
        Validate.notNull(lineList);
        PrintWriter out = null;
        try {
            File father = file.getParentFile();
            if(!father.exists()) {
                father.mkdirs();
            }
            out = new PrintWriter(file, code);
            for (String string : lineList) {
                out.println(string);
            }
        } catch (Exception e) {
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
    public void writeFileByTemplet(InputStream ins, File outFile, Table table) {
        List<String> lineList = readToLine(ins, SystemConfig.INPUT_CODE);
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
        Map<String,InputStream> result = new HashMap<>(64);
        for (String name : names) {
            InputStream stream = Class.class.getResourceAsStream(PathConfig.TEMPLET_DIR + name);
            if(stream == null){
                throw new RuntimeException(PathConfig.TEMPLET_DIR + name);
            }
            result.put(name, stream);
        }
        return result;
    }

    @Override
    public InputStream getTemplet(String templetPath) {
        return Class.class.getResourceAsStream(templetPath);
    }


    @Override
    public void writeAllFileByTemplet(Table table, String outDir, String templetDir) {
        File outDirFile = new File(outDir);
        outDirFile.mkdirs();
        File tmpletDirFile = new File(templetDir);
        File[] files = tmpletDirFile.listFiles(file -> file.isDirectory() || file.getName().endsWith(SystemConfig.SUFFIX));
        if(files == null) {
            return;
        }
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
        File outDirFile = new File(PathConfig.outCodeDir());
        outDirFile.mkdirs();
        Map<String, InputStream> allJarTemplet = getAllJarTemplet();
        Set<String> keySet = allJarTemplet.keySet();
        for (String key : keySet) {
            String fileName = LineUtil.buildName(key, table);
            List<String> lineList = readToLine(allJarTemplet.get(key), SystemConfig.INPUT_CODE);
            List<String> newLine = LineUtil.buildNewLine(lineList, table);
            write(PathConfig.outCodeDir() + "/" + fileName, newLine);
        }
    }

    @Override
    public void writeDataFile(Table table) {
        List<List<Object>> listList = DBUtil.query("SELECT * FROM " + table.getTableName() + " WHERE ROWNUM <= 200 ORDER BY 1");
        List<String> line = Container.databaseService.dataToLine(table, listList);
        write(new File(PathConfig.outSqlPath(table)), line);
    }

    @Override
    public void writeDataFile(Table table, String sql, String[] primaryColUpKeys) {
        List<String> line = Container.databaseService.custDataToLine(table, sql, primaryColUpKeys);
        write(new File(PathConfig.outSqlPathCust(table)), line);
    }

    @Override
    public void clear() {
        Container.ioService.clearWithReg(new File(PathConfig.outCodeDir()), new String[]{".+\\.+(java|xml|Table|PDC)"}, new String[]{});
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

    @Override
    public void writeCsv(File file,  Map<String, Map.Entry<String,Integer>> map) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, SystemConfig.INPUT_CODE);
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("columnName", "paramName", "score");
            CSVPrinter csvPrinter = new CSVPrinter(outputStreamWriter, csvFormat);
            map.forEach((key, value) -> {
                addColumn(csvPrinter, key, value.getKey(), value.getValue());
            });
            csvPrinter.flush();
            csvPrinter.close();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    private void addColumn(CSVPrinter csvPrinter, Object... objs){
        try {
            csvPrinter.printRecord(objs);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 比cpdetector 和 any23 好用
     * @param file
     * @return
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String getCharset(File file ) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
            bis.mark( 0 );
            int read = bis.read( first3Bytes, 0, 3 );
            if ( read == -1 ) {
                return charset;
            }
            if ( first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE ) {
                charset = "UTF-16LE";
                checked = true;
            }
            else if ( first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF ) {
                charset = "UTF-16BE";
                checked = true;
            }
            else if ( first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF ) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if ( !checked ) {
                //    int len = 0;
                int loc = 0;

                while ( (read = bis.read()) != -1 ) {
                    loc++;
                    if ( read >= 0xF0 ) {
                        break;
                    }
                    // 单独出现BF以下的，也算是GBK
                    if ( 0x80 <= read && read <= 0xBF )
                    {
                        break;
                    }
                    if ( 0xC0 <= read && read <= 0xDF ) {
                        read = bis.read();
                        // 双字节 (0xC0 - 0xDF) (0x80 - 0xBF),也可能在GB编码内
                        if ( 0x80 <= read && read <= 0xBF )
                        {
                            continue;
                        } else {
                            break;
                        }
                    }
                    // 也有可能出错，但是几率较小
                    else if ( 0xE0 <= read && read <= 0xEF ) {
                        read = bis.read();
                        if ( 0x80 <= read && read <= 0xBF ) {
                            read = bis.read();
                            if ( 0x80 <= read && read <= 0xBF ) {
                                charset = "UTF-8";
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
            }

            bis.close();
        } catch ( Exception e ) {
            throw new RuntimeException(e.getMessage());
        }

        return charset;
    }
}
