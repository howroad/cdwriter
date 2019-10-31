package com.howroad.frame.jframe;


import java.awt.*;

import javax.swing.*;

import com.howroad.frame.panel.*;
import com.howroad.log.PanelLog;


/**
 * <p>Title: ShowFrame.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年3月5日 下午2:37:58
 * 
 */
public class ShowFrame extends JFrame {
    
    private static final long serialVersionUID = -4760857055691612569L;
    public static final int TEXT_LENGTH = 20;
    private static final String SEQ_DIR_0 = "前置";

    private JPanel contentPanel = new JPanel(new FlowLayout());

    /** 内容，后两位参数是间距 */
    private JPanel settingPanel = new JPanel(new GridLayout(12, 2, 1, 1));

    private LogPanel logPanel = new LogPanel();

    private JPanel sqlPanel = new JPanel(new GridLayout(12, 2, 1, 1));

    /** settings start */
    private FilePane filePanel = new FilePane("...", TEXT_LENGTH);
    private TextPane outerDirPanel = new TextPane("outer:", TEXT_LENGTH);
    private TextPane uRLPanel = new TextPane("url:", TEXT_LENGTH);
    private TextPane userNamePanel = new TextPane("user:", TEXT_LENGTH);
    private TextPane passwordPanel = new TextPane("pwd:", TEXT_LENGTH);
    private SelectPanel fromExcelPanel = new SelectPanel("frmExl", TEXT_LENGTH, "false", "true");
    private SelectPanel fromDatebasePanel = new SelectPanel("frmDb", TEXT_LENGTH, "false", "true");
    private TextPane appNoPanel = new TextPane("appNo:", TEXT_LENGTH);
    private TextPane tablesPanel = new TextPane("tbls", TEXT_LENGTH);
    private SelectPanel seqDirPanel = new SelectPanel("seqDr:", TEXT_LENGTH, "前置", "后置");
    private JPanel btnPanel = new JPanel();
    
    private JPanel custPanel = new JPanel();

    private JButton conBtn = new JButton("conn");
    private JButton runBtn = new JButton("run");
    private JButton clearBtn = new JButton("clear");
    private JButton testBtn = new JButton("test");
    
    private JButton showSqlBtn = new JButton("sql>>");
    private JButton logBtn = new JButton("log>>");
    /** settings end */

    /**export Sql start*/
    private TextPane sqlTables = new TextPane("tbls(;):", TEXT_LENGTH);
    private TextPane sqlSqls = new TextPane("sqls(;):", TEXT_LENGTH);
    private TextPane sqlPks = new TextPane("pks(;,):", TEXT_LENGTH);
    private JPanel sqlBtnPanel = new JPanel();
    private JButton createSql = new JButton("生成sql");


    //JOIN
    private JoinSqlLine joinSqlLine1 = new JoinSqlLine(true);
    private JoinSqlLine joinSqlLine2 = new JoinSqlLine(false);
    private JoinSqlLine joinSqlLine3 = new JoinSqlLine(false);
    private JoinSqlLine joinSqlLine4 = new JoinSqlLine(false);
    private JoinSqlLine joinSqlLine5 = new JoinSqlLine(false);

    private JPanel joinBtnPanel = new JPanel();
    private JButton joinBtnOnSqlId = new JButton("按照sqlId生成");
    private JButton joinBtnOnColumn = new JButton("按照字段生成");
    /**export Sql end*/


    public ShowFrame() {

        PanelLog.initLog(this.logPanel);
        
        this.setBounds(0, 0, 280, 350);
        // 无相对位置
        this.setLocationRelativeTo(null);
        // 不可设置大小
        this.setResizable(false);
        // 关闭样式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        btnPanel.add(conBtn);
        btnPanel.add(runBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(testBtn);
        
        custPanel.add(showSqlBtn);
        custPanel.add(logBtn);
        
        this.settingPanel.add(filePanel);
        this.settingPanel.add(outerDirPanel);
        this.settingPanel.add(uRLPanel);
        this.settingPanel.add(userNamePanel);
        this.settingPanel.add(passwordPanel);
        this.settingPanel.add(appNoPanel);
        this.settingPanel.add(seqDirPanel);
        this.settingPanel.add(tablesPanel);
        this.settingPanel.add(fromExcelPanel);
        this.settingPanel.add(fromDatebasePanel);
        this.settingPanel.add(btnPanel);
        this.settingPanel.add(custPanel);

        /**sql panel start*/
        sqlPanel.add(sqlTables);
        sqlPanel.add(sqlSqls);
        sqlPanel.add(sqlPks);
        sqlBtnPanel.add(createSql);
        sqlPanel.add(sqlBtnPanel);

        sqlPanel.add(joinSqlLine1);
        sqlPanel.add(joinSqlLine2);
        sqlPanel.add(joinSqlLine3);
        sqlPanel.add(joinSqlLine4);
        sqlPanel.add(joinSqlLine5);

        joinBtnPanel.add(joinBtnOnSqlId);
        joinBtnPanel.add(joinBtnOnColumn);
        sqlPanel.add(joinBtnPanel);

        /**sql panel end*/

        this.contentPanel.add(settingPanel);
        this.contentPanel.add(logPanel);
        this.contentPanel.add(sqlPanel);

        this.setTitle("dbWriter v2.9.9");
        this.setContentPane(contentPanel);
        pack();

    }

    
}
