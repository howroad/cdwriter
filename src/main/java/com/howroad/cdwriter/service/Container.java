package com.howroad.cdwriter.service;

import com.howroad.cdwriter.service.impl.CoreServiceImpl;
import com.howroad.cdwriter.service.impl.DatabaseServiceImpl;
import com.howroad.cdwriter.service.impl.IoServiceImpl;

/**
 * <p>Title: Container.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-09 18:10
 */
public class Container {
    public static IDatabaseService databaseService;
    public static ICoreService coreService;
    public static IIoService ioService;

    static{
        databaseService = new DatabaseServiceImpl();
        coreService = new CoreServiceImpl();
        ioService = new IoServiceImpl();
    }
}
