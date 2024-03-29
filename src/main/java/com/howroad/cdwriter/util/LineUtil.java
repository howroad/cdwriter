package com.howroad.cdwriter.util;

import com.howroad.cdwriter.constants.TableContans;
import com.howroad.cdwriter.model.MyParam;
import com.howroad.cdwriter.model.Table;
import com.howroad.cdwriter.rule.CommonMap;
import com.howroad.cdwriter.rule.FileNameMap;
import com.howroad.cdwriter.rule.WithoutLastMap;
import com.howroad.log.PanelLog;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: LineUtil.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-10-12 16:27
 */
public class LineUtil {
    public static List<String> buildNewLine(List<String> lineList, Table table) {

        List<String> result = new ArrayList<String>();
        /** 循环标识 */
        boolean loop = false;
        /** 是否取消第一个 */
        boolean clearFirst = false;
        /** 循环体 */
        List<String> loopLine = new ArrayList<String>();

        boolean delete = false;

        String outStr = null;
        for (String line : lineList) {
            if(line.matches("^.*@\\{/delete}.*$")) {
                delete = false;
                continue;
            }
            else if(line.matches("^.*@\\{delete}.*$")) {
                delete = true;
                continue;
            }
            else if(delete){
                continue;
            }
            //循环种
            else if(loop) {
                // 遇到结束标识 解析循环体中的模板
                if(line.matches("^.*@\\{end}.*$")) {
                    loop = false;
                    for (ListIterator<MyParam> iterator = table.getParamList().listIterator(); iterator.hasNext();) {
                        // 清除第一个属性信息
                        if(!iterator.hasPrevious() && clearFirst) {
                            iterator.next();
                            continue;
                        }
                        MyParam param = iterator.next();
                        for (String string : loopLine) {
                            if(string.matches("^.*@if1@.*$")) {
                                if(param.getType().getColumnTypeName().startsWith(TableContans.NUMBER)) {
                                    string = string.replace("@if1@","DECODE(#list[].$param{paramName}#,NULL,NULL,#list[].$param{paramName}#) AS $param{columnName}$split{,}");
                                }else {
                                    string = string.replace("@if1@", "#list[].$param{paramName}# AS $param{columnName}$split{,}");
                                }
                            }

                            outStr = replaceTemplate(string, param.getMap(), "param");
                            outStr = replaceTemplate(outStr, table.getMap(), "table");

                            if(iterator.hasNext()) {
                                outStr = replaceTemplate(outStr, CommonMap.map, "split");
                            }else {
                                outStr = replaceTemplate(outStr, WithoutLastMap.map, "split");
                            }
                            result.add(outStr);
                        }
                    }
                    // 不是结束标识，继续添加循环体
                }else {
                    loopLine.add(line);
                }
                // 开始进入循环体，设置标识
            }else if(line.matches("^.*@\\{start}.*$")) {
                loopLine.clear();
                loop = true;
                clearFirst = false;
            }else if(line.matches("^.*@\\{startFrom2}.*$")) {
                loopLine.clear();
                loop = true;
                clearFirst = true;
                // 不是循环，解析模板
            } else {
                outStr = replaceTemplate(line, CommonMap.map, "common");
                outStr = replaceTemplate(outStr, table.getMap(), "table");
                result.add(outStr);
            }
        }
        return result;
    }


    public static String replaceTemplate(String template, Map<String, String> tokens, String type) {

        //生成匹配模式的正则表达式
        String patternString = "(?i)\\$"+ type + "\\{(" + StringUtils.join(tokens.keySet(), "|") + ")}";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        //两个方法：appendReplacement, appendTail
        //appendReplacement方法：sb是一个StringBuffer，replaceContext待替换的字符串，这个方法会把匹配到的内容替换为replaceContext，并且把从上次替换的位置到这次替换位置之间的字符串也拿到，然后，加上这次替换后的结果一起追加到StringBuffer里（假如这次替换是第一次替换，那就是只追加替换后的字符串啦）。
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            String key = matcher.group(1);
            String value = tokens.get(key) == null ? tokens.get(key.toUpperCase()) == null ? "" : tokens.get(key.toUpperCase()) : tokens.get(key);
            matcher.appendReplacement(sb, value);
        }
        // 把最后一次匹配到内容之后的字符串追加到StringBuffer中
        matcher.appendTail(sb);

        return sb.toString();

    }

    public static String buildCustName(String oldLine, Table table){
        String result = FileNameMap.getCustName(oldLine);
        result = replaceTemplate(result, table.getMap(), "table");
        result = replaceTemplate(result, CommonMap.map, "common");
        return result;
    }
    public static String buildName(String oldLine, Table table){
        String result = FileNameMap.getLocalName(oldLine);
        result = replaceTemplate(result, table.getMap(), "table");
        result = replaceTemplate(result, CommonMap.map, "common");
        return result;
    }


    public static List<String> rebuildFile(List<String> list){
        final String reg = "package\\s+.+";
        final String reg2 = "public\\s+class\\s\\w+(\\s+extends.+)\\{";
        final String reg3 = "\\s+@Oweride.+";
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            if(line == null){
                continue;
            }
            if(line.matches(reg) || line.matches(reg3)){
                PanelLog.log("【重新编辑java文件】删除 " + list.get(i));
                list.set(i,"");
                continue;
            }
            if(line.matches(reg2) ){
                PanelLog.log("【重新编辑java文件】编辑前: " + list.get(i));
                list.set(i,line.replaceAll("\\sextends\\s+\\w+(?=(\\s+implements\\s+\\w+)?\\s?\\{)",""));
                PanelLog.log("【重新编辑java文件】编辑后: " + list.get(i));
                break;
            }
        }
        return list;
    }


}
