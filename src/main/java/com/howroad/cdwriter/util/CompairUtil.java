package com.howroad.cdwriter.util;

import com.howroad.cdwriter.builder.TableBuilder;
import com.howroad.cdwriter.conf.Config;
import com.howroad.cdwriter.model.MyParam;
import com.howroad.cdwriter.model.Table;
import com.howroad.temp.model.Account;
import org.apache.commons.lang3.Validate;
import org.apache.poi.hssf.record.formula.functions.T;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: CompairUtil.java</p>
 * <p>Description: 字符串比较工具</p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2020-01-13 15:31
 */
public class CompairUtil {
    
    public static void main(String[] args) {
        Config.init();
        Table table = TableBuilder.buildTableFromDB("aims_account");
        Class clazz = Account.class;
        System.out.println(map(table,clazz));

    }

    public static Map<String,String> map(Table table, Class<?> clazz){
        Validate.isTrue(table != null);
        Validate.isTrue(table.getParamList() != null);
        Map<String,String> result = new HashMap<String,String>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (MyParam myParam : table.getParamList()) {
            String finalValue = null;
            int finalScore = 99;
            String columnName = myParam.getColumnName().toUpperCase();
            for (Field field : declaredFields) {
                String paramNameOld = field.getName();
                String paramName = field.getName().toUpperCase();
                int score = compair(columnName, paramName);
                if(finalScore == 0 && score == 0){
                    throw new RuntimeException(columnName + "无法从中选择：" + finalValue + "、" + "paramName");
                }
                if(score < finalScore){
                    finalScore = score;
                    finalValue = paramNameOld;
                }
            }
            result.put(columnName, finalValue);
        }
        return result;
    }
    
    public static int compair(String str1, String str2){
        int result = 0;
        if(str1 != null && str2 != null){
            String a = str1.toUpperCase().replaceAll("_","");
            String b = str2.toUpperCase().replaceAll("_","");
            if(a.equals(b)){
                result = 0;
            }else{
                result = minDistance(a, b);
            }
        }
        return result;
    }
    /**
     * 比较两个字符串的相识度
     * 核心算法：用一个二维数组记录每个字符串是否相同，如果相同记为0，不相同记为1，每行每列相同个数累加
     * 则数组最后一个数为不相同的总数，从而判断这两个字符的相识度
     *
     * @param str
     * @param target
     * @return
     */
    private static int minDistance(String str, String target) {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }


    /**
     * 获取最小的值
     */
    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     */
    public static float getSimilarityRatio(String str, String target) {
        int max = Math.max(str.length(), target.length());
        return 1 - (float) minDistance(str, target) / max;
    }

    static class CompairBox {
        String tableName;
        String className;
        int score;
    }
}
