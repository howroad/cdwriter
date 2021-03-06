package com.howroad.temp.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * <p>
 * Title: BaseDao.java
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * @author luhao
 * 
 * @since：2018年12月27日 上午10:29:54
 * 
 */
public class BaseDao {
    private static SqlMapClient sqlMapClient = null;
    private static SqlMapClientTemplate sqlMapClientTemplate = null;
    // 读取配置文件
    static {
        try {
            Reader reader = Resources.getResourceAsReader("com/howroad/temp/dao/SqlMapConfig.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
    }
    public SqlMapClientTemplate getSqlMapClientTemplate() {
        return sqlMapClientTemplate;
    }
    protected String getStatement(String statement){
        return "TEMP." + getClass().getSimpleName() + "." + statement;
    }
}
class SqlMapClientTemplate {
    private SqlMapClient sqlMapClient;
    public SqlMapClientTemplate (SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
    public Object insert(String str,Object obj) {
        try {
            return sqlMapClient.insert(str, obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void delete(String str,Object obj) {
        try {
            sqlMapClient.delete(str, obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void update(String str,Object obj) {
        try {
            sqlMapClient.update(str, obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<?> queryForList(String str,Object obj) {
        try {
            return sqlMapClient.queryForList(str, obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Object queryForObject(String str,Object obj) {
        try {
            return sqlMapClient.queryForObject(str, obj);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
