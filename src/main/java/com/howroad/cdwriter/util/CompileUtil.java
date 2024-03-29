package com.howroad.cdwriter.util;

/**
 * <p>Title: CompileUtil.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2020-01-14 11:20
 */

import com.howroad.log.PanelLog;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * <p>Title: TestNature.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 *
 * @since：2019年1月9日 下午1:35:56
 *
 */
public class CompileUtil {

    public static void main(String[] args) {
        String path = "E:\\Project\\guodiantou2P\\cdWriter\\in\\Account.java";
        compile(path,null);
        addClassLoader("E:\\Project\\guodiantou2P\\cdWriter\\in");
        Class<?> account = null;
        try {
            account = Class.forName("Account");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        PanelLog.log(account);
    }

    public static void compile(String path, String outPath) {
        try {
            JavaCompiler javac;
            javac = ToolProvider.getSystemJavaCompiler();
            int compilationResult = -1;
            compilationResult = javac.run(null, PanelLog.out, PanelLog.out, "-g", "-encoding", "utf-8", "-verbose", path);
            if(compilationResult != 0){
                throw new RuntimeException("编译失败！");
            }
            PanelLog.log("编译成功！");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void addClassLoader(String classPathDir) {


        URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        try {
            URL url = new File(classPathDir).toURI().toURL();
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke(classloader, new Object[]{url});
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
