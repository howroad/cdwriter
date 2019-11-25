package com.howroad.cdwriter.service;

import com.howroad.frame.jframe.ShowFrame;

/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * @author luhao
 * @since：2019-09-12 16:32
 */
public interface ICoreService {
    boolean testCoonect();
    void createFromXls();
    void createFromDb();
    void clear();
    void createCustSql(String tbNamesStr,String sqlsStr,String pkNamesStr);
}
