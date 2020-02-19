package com.howroad.cdwriter.builder;

import com.google.common.base.CaseFormat;
import com.howroad.cdwriter.conf.PageConfig;
import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.conf.SystemConfig;
import com.howroad.cdwriter.model.MyParam;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.service.Container;
import com.howroad.cdwriter.util.CompairUtil;
import com.howroad.cdwriter.util.CompileUtil;
import com.howroad.cdwriter.util.DBUtil;
import com.howroad.cdwriter.util.ExcelUtil;
import com.howroad.cdwriter.util.LineUtil;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>Title: TableBuilder.java</p>
 *
 * <p>Description: Table构建类</p>
 *
 * @author luhao
 * 
 * @since：2019年2月1日 上午11:53:09
 * 
 */
public class TableBuilder {
    private TableBuilder () {}
    
    /**
     * 从数据库构建Table
     * @param tableName
     * @return Table
     * @author luhao
     * @since：2019年2月25日 下午2:07:19
     */
    public static Table buildTableFromDB(String tableName) {
        tableName = tableName.toUpperCase();
        DatabaseMetaData db;
        // 字段信息
        ResultSet rs = null;
        // 表信息
        ResultSet rsTable = null;
        String tableRemark = null;
        List<MyParam> paramList = new ArrayList<MyParam>();
        try {
            Connection conn = DBUtil.getConn();
            ((OracleConnection) conn).setRemarksReporting(true);
            db = conn.getMetaData();
            rs = db.getColumns(null, PageConfig.USER.toUpperCase(), tableName.toUpperCase(), null);
            rsTable = db.getTables(null, PageConfig.USER.toUpperCase(), tableName.toUpperCase(), new String[] {"TABLE"});
            if(rsTable.next()) {
                tableRemark = rsTable.getString("REMARKS");
            }
            while (rs.next()) {
                // 列名
                String columnName = rs.getString("COLUMN_NAME");
                // 字段名称
                String paramName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                // 数据类型
                int columnType = rs.getInt("DATA_TYPE");
                // 小数位数
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");
                // 注释
                String remark = rs.getString("REMARKS");
                //精度
                int columnSize = rs.getInt("COLUMN_SIZE");
                //是否为空
                int nullAble = rs.getInt("NULLABLE");//是否允许为null
                //默认值
                String columnDef = rs.getString("COLUMN_DEF");
                
                MyParam param = new MyParam(paramName, columnName, remark, columnType, columnSize, decimalDigits, nullAble, columnDef);
                paramList.add(param);
            }
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(rsTable != null){
                    rsTable.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        if(paramList.isEmpty()) {
            throw new RuntimeException(tableName + "表中无字段，或该表不存在！");
        }
        Table table = new Table(tableName, tableRemark ,paramList);
        return table;
    }

    /**
     * 从Excel中构建Table
     * @param sheetNo
     * @return List<Table>
     * @author luhao
     * @since：2019年2月25日 下午2:11:02
     */
    public static List<Table> buildTableFromExcel(int sheetNo) {
        //excel 导入数据
        File file = new File(PathConfig.EXCEL_PATH());
        List<List<String>> dataList= null;
        List<Table> tableList = new ArrayList<Table>();
        
        Table tempTable = null;
        List<MyParam> tempParamList = null;
        try {
            dataList = ExcelUtil.importExcel(file,sheetNo);
            for (ListIterator<List<String>> iterator = dataList.listIterator(); iterator.hasNext();) {
                List<String> list = iterator.next();
                if("END".equalsIgnoreCase(list.get(0))) {
                    if(tempTable == null){
                        break;
                    }
                    Table table = tempTable.clone();
                    tableList.add(table);
                    break;
                }
                
                String columnName = list.size() > 0 ? list.get(0) : "";
                //PanelLog.log(columnName);
                String type = list.size() > 1 ? list.get(1) : "";
                String commont = list.size() > 2 ? list.get(2) : "";
                String nullableStr = list.size() > 3 ? list.get(3) : "";
                String defaultV = list.size() > 4 ? list.get(4) : "";

                if(StringUtils.isEmpty(columnName) && StringUtils.isEmpty(type) && StringUtils.isEmpty(commont)) {
                    continue;
                }

                //表名
                if(StringUtils.isEmpty(type)) {
                    //非第一次，把原来的数据放入
                    if(tempTable != null && tempParamList != null) {
                        tempTable.setParamList(tempParamList);
                        Table table = tempTable.clone();
                        tableList.add(table);
                    }
                    tempParamList = new ArrayList<MyParam>();
                    tempTable = new Table(columnName, commont ,tempParamList);
                }else {
                    //属性信息
                    tempParamList.add(new MyParam(commont, columnName, type, nullableStr, defaultV));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return tableList;
    }

    public static List<Table> buildTableFromNames(List<String> tbNames) {
        List<Table> resultList = new ArrayList<Table>();
        for (String tableName : tbNames) {
            Table table = buildTableFromDB(tableName);
            resultList.add(table);
        }
        return resultList;
    }

    public static List<Table> buildTableFromNames(String[] tbNames) {
        List<Table> resultList = new ArrayList<Table>();
        for (String tableName : tbNames) {
            Table table = buildTableFromDB(tableName);
            resultList.add(table);
        }
        return resultList;
    }

    public static List<Class<?>> buildClazzFromNames(String[] modelFiles) {
        List<Class<?>> list = new ArrayList<>();
        for (String modelFile : modelFiles) {
            List<String> lineList = Container.ioService.readToLine(new File(PathConfig.IN_CODE_DIR() + modelFile));
            File javaFile = new File(PathConfig.IN_CODE_DIR() + modelFile);
            LineUtil.replacePackage(lineList);
            Container.ioService.write(javaFile, lineList, "utf8");
            //编译该java文件到虚拟机
            CompileUtil.compile(PathConfig.IN_CODE_DIR() + modelFile,null);
            //加载自定义的classPath
            CompileUtil.addClassLoader(PathConfig.IN_CODE_DIR());
            Class<?> clazz = null;
            try {
                clazz = Class.forName(modelFile.replace(".java",""));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

            list.add(clazz);
        }
        return list;
    }
}
