package com.howroad.log;

import com.howroad.frame.panel.LogPanel;

import java.io.OutputStream;
import java.io.PrintStream;

public class PanelLog {

    private static LogPanel logPanel;

    public static OutputStream out;

    private PanelLog(){}

    public static LogPanel getLog(){
        if(logPanel == null){
            throw new RuntimeException("无法获取日志信息！");
        }else{
            return logPanel;
        }
    }

    public static void initLog(LogPanel logPanel0){
        if(logPanel == null){
            logPanel = logPanel0;
        }
        out = logPanel0.getOutputStream();
    }

    public static void log(Object log0){
        logPanel.log(log0 == null ? null : log0.toString());
    }
}
