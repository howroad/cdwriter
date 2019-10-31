package com.howroad.cdwriter.service.impl;

import com.howroad.cdwriter.builder.TableBuilder;
import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.service.Container;
import com.howroad.cdwriter.service.ICoreService;
import com.howroad.log.PanelLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    public void testCoonect() {
        String sql = "SELECT 1 FROM DUAL";
        Container.databaseService.query(sql);
    }

    @Override
    public void handelRun() {
        //TODO
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
            String filName = PathConfig.OUT_WORK_SPACE + "\\sqls\\" + table.getTableName() + ".SQL";
            File file = new File(filName);
            file.getParentFile().mkdirs();
            Container.ioService.writeDataFile(table,sql,primaryKey,filName);
            PanelLog.log("build " + tableName + ".SQL down...");
        }
    }
}
