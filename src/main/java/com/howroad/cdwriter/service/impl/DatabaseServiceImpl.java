package com.howroad.cdwriter.service.impl;

import com.google.common.base.CaseFormat;
import com.howroad.cdwriter.builder.TableBuilder;
import com.howroad.cdwriter.conf.Config;
import com.howroad.cdwriter.conf.PageConfig;
import com.howroad.cdwriter.model.MyParam;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.service.Container;
import com.howroad.cdwriter.service.IDatabaseService;
import com.howroad.cdwriter.util.DBUtil;
import com.howroad.cdwriter.util.DataUtil;
import com.howroad.cdwriter.util.ExcelUtil;
import com.howroad.frame.panel.JoinSqlLine;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>Title: SqlServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 13:21
 */
public class DatabaseServiceImpl implements IDatabaseService {

    @Override
    public File createJoinSql(List<JoinSqlLine> lineList, File templet, String sqlId) {
        return null;
    }

    @Override
    public List<Table> getTable(List<JoinSqlLine> lineList) {
        final List<String> tableNames = getTableNames(lineList);
        return TableBuilder.buildTableFromNames(tableNames);
    }

    @Override
    public List<String> getTableNames(List<JoinSqlLine> lineList) {
        List<String> tableNames = new ArrayList<String>(16);
        lineList.forEach(joinSqlLine -> {
            String tb1 = joinSqlLine.getTb1().getText();
            String tb2 = joinSqlLine.getTb2().getText();
            if(StringUtils.isNotBlank(tb1)){
                tableNames.add(tb1);
            }
            if(StringUtils.isNotBlank(tb2)){
                tableNames.add(tb2);
            }
        });
        return tableNames;
    }

    @Override
    public List<List<Object>> query(String sql) {
        return DBUtil.query(sql);

    }

    @Override
    public String defaultDDL(String tableName) {
        tableName = tableName.toUpperCase();
        String sql = "SELECT DBMS_METADATA.GET_DDL('TABLE','" + tableName + "') FROM DUAL";
        List<List<Object>> list = DBUtil.query(sql);
        String result = null;
        if(!list.isEmpty()){
            Clob clob = (Clob)list.get(0).get(0);
            result = DataUtil.clobToString(clob);
        }
        return result;
    }


    @Override
    public List<String> custDataToLine(Table table, String sql, String[] primaryColUpKeys) {
        List<String> resultList =  new ArrayList<String>();
        List<List<Object>> dataList = this.query(sql);
        String[] primaryKeyValues = new String[primaryColUpKeys.length];
        for (List<Object> list : dataList) {
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            sb1.append("INSERT INTO " + table.getTableName() + "(");
            for (ListIterator<MyParam> iterator = table.getParamList().listIterator(); iterator.hasNext();) {
                MyParam param = iterator.next();
                String columnName = param.getColumnName();
                if (iterator.hasNext()) {
                    sb1.append(columnName + ",");
                } else {
                    sb1.append(columnName + ") ");
                }
            }
            resultList.add(sb1.toString());
            sb2.append("SELECT ");
            int index = 0;
            for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
                boolean last = false;
                Object object = iterator.next();
                MyParam param = table.getParamList().get(index++);
                String columnName = param.getColumnName();
                String value = table.getInsertValue(object, param);
                for (int i = 0; i < primaryKeyValues.length; i++) {
                    if(columnName.equals(primaryColUpKeys[i])) {
                        primaryKeyValues[i] = value;
                    }
                }
                last = !iterator.hasNext();
                if(last) {
                    sb2.append(value);
                }else {
                    sb2.append(value + ",");
                }
            }
            StringBuffer whereStr = new StringBuffer(128);
            for (int i = 0; i < primaryColUpKeys.length; i++) {
                String primaryColUpKey = primaryColUpKeys[i];
                String primaryKeyValue = primaryKeyValues[i];
                if(i > 0) {
                    whereStr.append(" AND ");
                }
                whereStr.append(primaryColUpKey + " = " + primaryKeyValue);
            }
            sb2.append(" FROM DUAL ");
            resultList.add(sb2.toString());
            resultList.add("WHERE NOT EXISTS (SELECT 1 FROM " + table.getTableName() + " WHERE " + whereStr + ")");
            resultList.add("/");

        }
        return resultList;
    }

    @Override
    public List<String> dataToLine(Table table, List<List<Object>> dataList) {
        List<String> resultList = new ArrayList<String>();
        String primaryKeyColumnName = table.getParamList().get(0).getColumnName();
        for (List<Object> list : dataList) {
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            String primaryKeyValue = String.valueOf(list.get(0));
            sb1.append("INSERT INTO " + table.getTableName() + "(");
            for (ListIterator<MyParam> iterator = table.getParamList().listIterator(); iterator.hasNext();) {
                MyParam param = iterator.next();
                if (iterator.hasNext()) {
                    sb1.append(param.getColumnName() + ",");
                } else {
                    sb1.append(param.getColumnName() + ") ");
                }
            }
            resultList.add(sb1.toString());
            sb2.append("SELECT ");
            int index = 0;
            for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
                boolean first = false;
                boolean last = false;
                first = !iterator.hasPrevious();
                Object object = iterator.next();
                String value = table.getInsertValue(object, table.getParamList().get(index++));
                last = !iterator.hasNext();
                boolean use = false;//此处不应有序列
                if(last) {
                    sb2.append(value);
                }else {
                    sb2.append(value + ",");
                }
            }
            sb2.append(" FROM DUAL ");
            resultList.add(sb2.toString());
            resultList.add("WHERE NOT EXISTS (SELECT 1 FROM " + table.getTableName() + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "')");
            resultList.add("/");
        }
        return resultList;
    }

}
