package com.howroad.cdwriter.util;

/**
 * <p>Title: VersionUtil.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2020-01-19 16:23
 */
public class VersionUtil {
    public static void main(String[] args) {
        String titleReg = "this\\.setTitle\\(\"cdWriter\\s([\\d\\.]+)\\showroad\".+";
        String versionReg = "<version>([\\d\\.]+)</version>";
        String str2 = "\t<version>1.01.15</version>\n";
    }
}
