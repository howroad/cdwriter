package com.howroad.cdwriter.service.impl;

import com.howroad.cdwriter.builder.TableBuilder;
import com.howroad.cdwriter.conf.PageConfig;
import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.service.Container;
import com.howroad.cdwriter.service.ICoreService;
import com.howroad.cdwriter.util.CompairUtil;
import com.howroad.log.PanelLog;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ICoreServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-09 10:20
 */
public class CoreServiceImpl implements ICoreService {

    @Override
    public boolean testCoonect() {
        String sql = "SELECT 1 FROM DUAL";
        try{
            List<List<Object>> result = Container.databaseService.query(sql);
            return result != null && result.size() > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void createFromXls() {
        List<Table> tables = TableBuilder.buildTableFromExcel(0);
        for (Table table : tables) {
            Container.ioService.writeAllFileByJarTemplet(table);
            Container.ioService.writeAllFileByTemplet(table, PathConfig.outCodeDir(), PathConfig.custTempletDir());
        }

        List<Table> tableAddColumns = TableBuilder.buildTableFromExcel(1);
        for (Table table : tableAddColumns) {
            //新增字段的模版
            InputStream addColumnTemplet = Container.ioService.getTemplet(PathConfig.ADD_COLUMN_TEMPLET);
            Container.ioService.writeFileByTemplet(addColumnTemplet, new File(PathConfig.addColumnPath(table)),table);
        }

        List<Table> tableModifyColumns = TableBuilder.buildTableFromExcel(2);
        for (Table table : tableModifyColumns) {
            //修改字段的模版
            InputStream modifyColumnTemplet = Container.ioService.getTemplet(PathConfig.MODIFY_COLUMN_TEMPLET);
            Container.ioService.writeFileByTemplet(modifyColumnTemplet, new File(PathConfig.modifyColumnPath(table)),table);
       }
    }

    @Override
    public void createFromDb() {
        List<Table> tables = TableBuilder.buildTableFromNames(PageConfig.tablesFromDB);
        for (Table table : tables) {
            Container.ioService.writeAllFileByJarTemplet(table);
            Container.ioService.writeAllFileByTemplet(table, PathConfig.outCodeDir(), PathConfig.custTempletDir());
            Container.ioService.writeDataFile(table);
        }

    }

    @Override
    public void clear() {
        Container.ioService.clear();
    }


    @Override
    public void createCustSql(String tbNamesStr, String sqlsStr, String pkNamesStr) {
        String[] tableNames = tbNamesStr.split(";");
        String[] sqls = sqlsStr.split(";");
        String[] primaryKeyStr = pkNamesStr.split(";");
        List<String[]> primaryKeys = new ArrayList<String[]>();
        for (String pk: primaryKeyStr) {
            primaryKeys.add(pk.split(","));
        }

        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            String sql = sqls[i];
            String[] primaryKey = primaryKeys.get(i);
            Table table = TableBuilder.buildTableFromDB(tableName);
            Container.ioService.writeDataFile(table,sql,primaryKey);
            PanelLog.log("build " + tableName + ".SQL(cust) down...");
        }
    }

    @Override
    public void createFromDbAndFile() {
        List<Table> tables = TableBuilder.buildTableFromNames(PageConfig.tablesFromDB);
        List<Class<?>> classList = TableBuilder.buildClazzFromNames(PageConfig.modelFiles);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String fmt = sdf.format(new Date());
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            Class<?> clazz = classList.get(i);
            Map<String, Map.Entry<String, Integer>> map = CompairUtil.map(table, clazz);
            Container.ioService.writeCsv(new File(PathConfig.csvPath() + "/" + i + "_" + fmt + ".csv"), map);
            table.reloadColumnMap(map);
            Container.ioService.writeAllFileByJarTemplet(table);
            Container.ioService.writeAllFileByTemplet(table, PathConfig.outCodeDir(), PathConfig.custTempletDir());
            Container.ioService.writeDataFile(table);
        }
    }
}
