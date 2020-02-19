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
        System.out.println(account);
        PanelLog.log(account);
    }

    public static void compile(String path, String outPath) {
        try {
            JavaCompiler javac;
            javac = ToolProvider.getSystemJavaCompiler();
            int compilationResult = -1;
            compilationResult = javac.run(null, null, null, "-g", "-verbose", path);
            PanelLog.log(compilationResult == 0 ? "编译成功：" + path : "编译失败：" + path );
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
//            Class<?> account = classloader.loadClass("Account");
//            Class cl = Class.forName("Account", true, classloader);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
