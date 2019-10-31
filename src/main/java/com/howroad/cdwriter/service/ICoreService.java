package com.howroad.cdwriter.service;

/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * @author luhao
 * @since：2019-09-12 16:32
 */
public interface ICoreService {
    void testCoonect();
    void handelRun();
    void clear();
    void createCustSql(String tbNamesStr,String sqlsStr,String pkNamesStr);
}
