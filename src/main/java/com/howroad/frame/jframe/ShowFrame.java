package com.howroad.frame.jframe;


import com.howroad.cdwriter.conf.Config;
import com.howroad.cdwriter.conf.PageConfig;
import com.howroad.cdwriter.conf.PathConfig;
import com.howroad.cdwriter.conf.VersionConfig;
import com.howroad.cdwriter.rule.CommonMap;
import com.howroad.cdwriter.service.Container;
import com.howroad.frame.panel.FilePane;
import com.howroad.frame.panel.JoinSqlLine;
import com.howroad.frame.panel.LogPanel;
import com.howroad.frame.panel.SelectPanel;
import com.howroad.frame.panel.TextPane;
import com.howroad.log.PanelLog;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;
import java.util.function.Consumer;


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
    private JPanel settingPanel = new JPanel(new GridLayout(10, 2, 1, 1));

    private LogPanel logPanel = new LogPanel();
    private  BoxLayout boxLayout = new BoxLayout(logPanel,BoxLayout.Y_AXIS);

    private JPanel sqlPanel = new JPanel(new GridLayout(9, 2, 1, 1));

    /** settings start */
    private FilePane filePanel = new FilePane("...", TEXT_LENGTH, "请选择工作空间");
    private TextPane urlPanel = new TextPane("url:", TEXT_LENGTH, "请输入URL,例如：192.168.20.35:1521:sas");
    private TextPane userNamePanel = new TextPane("user:", TEXT_LENGTH,"请输入用户名，例如：spic1204");
    private TextPane passwordPanel = new TextPane("pwd:", TEXT_LENGTH,"请输入密码，例如：123456");

    private TextPane appNoPanel = new TextPane("appNo:", TEXT_LENGTH,"生成代码的模块名，将影响包名、xml文件名");
    private TextPane tablesPanel = new TextPane("tbls", TEXT_LENGTH,"从数据库读取表名生成代码，用逗号分割，例如：aims_account,aims_ebank");
    private TextPane filesPanel = new TextPane("files", TEXT_LENGTH,"针对已存在的Java类和已存在的表，自动分析对照关系，生成代码，可以在log中查看详情。");
    private SelectPanel seqDirPanel = new SelectPanel("T_seq:", TEXT_LENGTH, "自动生成的序列文件命名规则_SEQ是否后置", "true", "false");
    private JPanel btnPanel = new JPanel();

    private JPanel custPanel = new JPanel();

    private JButton dbFile = new JButton("Db+F");
    private JButton conBtn = new JButton("cnn");
    private JButton runBtnExcel = new JButton("RnEx");
    private JButton clearBtn = new JButton("clr");
    private JButton runBtnDb = new JButton("RnDb");

    private JButton showSqlBtn = new JButton("sql>");
    private JButton openEx = new JButton("opEx");
    private JButton logBtn = new JButton("lg>");
    /** settings end */

    /**export Sql start*/
    private TextPane sqlTables = new TextPane("tbls(;):", TEXT_LENGTH, "生成数据初始化脚本的关键表，用分号隔开");
    private TextPane sqlSqls = new TextPane("sqls(;):", TEXT_LENGTH, "用于选择生成的记录的sql，用分好分割");
    private TextPane sqlPks = new TextPane("pks(;,):", TEXT_LENGTH, "用于唯一确定一条记录，类似于主键，可以选择多个，例如：type_code,app_no;ebank_id");
    private JPanel sqlBtnPanel = new JPanel();
    private JButton createSql = new JButton("生成sql");


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
        btnPanel.add(runBtnExcel);
        btnPanel.add(runBtnDb);
        btnPanel.add(clearBtn);

        custPanel.add(openEx);
        custPanel.add(dbFile);
        custPanel.add(showSqlBtn);
        custPanel.add(logBtn);

        logPanel.setLayout(boxLayout);
        
        this.settingPanel.add(filePanel);
        this.settingPanel.add(urlPanel);
        this.settingPanel.add(userNamePanel);
        this.settingPanel.add(passwordPanel);
        this.settingPanel.add(appNoPanel);
        this.settingPanel.add(seqDirPanel);
        this.settingPanel.add(tablesPanel);
        this.settingPanel.add(filesPanel);
        this.settingPanel.add(btnPanel);
        this.settingPanel.add(custPanel);

        //sql panel start
        sqlPanel.add(sqlTables);
        sqlPanel.add(sqlSqls);
        sqlPanel.add(sqlPks);
        sqlBtnPanel.add(createSql);
        sqlPanel.add(sqlBtnPanel);
        //sql panel end

        this.contentPanel.add(settingPanel);
        this.contentPanel.add(logPanel);
        this.contentPanel.add(sqlPanel);

        this.setContentPane(contentPanel);
        //退出即关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        init();

        this.setTitle("cdWriter " + VersionConfig.cdWriterVersion + " howroad");

    }

    private void init() {
        Config.initClass();
        loadConfig();
        addListener();
        hidePanel(logPanel);
        hidePanel(sqlPanel);
        setAllToolTip();
    }

    private void loadConfig(){
        seqDirPanel.setText(BooleanUtils.toStringTrueFalse(PageConfig.SEQ_ON_LAST));
        filePanel.setText(PageConfig.WORK_SPACE);
        urlPanel.setText(PageConfig.URL == null ? "" : PageConfig.URL.replace("jdbc:oracle:thin:@", ""));
        userNamePanel.setText(PageConfig.USER);
        passwordPanel.setText(PageConfig.PASSWORD);
        appNoPanel.setText(PageConfig.appNo);
        tablesPanel.setText(PageConfig.tablesFromDB != null ? StringUtils.join(PageConfig.tablesFromDB,",") : "");
        filesPanel.setText(PageConfig.modelFiles != null ? StringUtils.join(PageConfig.modelFiles,",") : "");
    }

    private void reLoadConfig(){
        PageConfig.WORK_SPACE = filePanel.getText();
        PageConfig.URL = "jdbc:oracle:thin:@" + urlPanel.getText();
        PageConfig.USER = userNamePanel.getText();
        PageConfig.PASSWORD = passwordPanel.getText();
        PageConfig.appNo = appNoPanel.getText();
        PageConfig.tablesFromDB = tablesPanel.getText().split(",");
        PageConfig.modelFiles = filesPanel.getText().split(",");
        PageConfig.SEQ_ON_LAST = Boolean.valueOf(seqDirPanel.getText());
        CommonMap.init();
    }

    private void addBtnListener(JButton jButton, Consumer<ActionEvent> consumer) {
        jButton.addActionListener(e -> {
            try{
                consumer.accept(e);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                //报错信息将会打印在日志页面
                e1.printStackTrace();
            }
        });
    }

    private void addListener(){
        addBtnListener(conBtn, e -> {
            reLoadConfig();
            boolean b = Container.coreService.testCoonect();
            Validate.isTrue(b,"连接失败！");
            writeProperties();
            JOptionPane.showMessageDialog(null, "连接成功！");
        });
        addBtnListener(runBtnExcel, e ->{
            reLoadConfig();
            Container.coreService.createFromXls();
            writeProperties();
            JOptionPane.showMessageDialog(null, "生成成功！");
        });
        addBtnListener(runBtnDb, e ->{
            reLoadConfig();
            Container.coreService.createFromDb();
            JOptionPane.showMessageDialog(null, "生成成功！");
            writeProperties();
        });
        addBtnListener(dbFile, e ->{
            reLoadConfig();
            //清除class文件
            Container.ioService.clearWithReg(new File(PathConfig.inCodeDir()), new String[]{".+\\.class"}, new String[]{});
            Container.coreService.createFromDbAndFile();
            JOptionPane.showMessageDialog(null, "生成成功！");
            writeProperties();
        });
        addBtnListener(clearBtn, e ->{
            reLoadConfig();
            Container.coreService.clear();
            JOptionPane.showMessageDialog(null, "清除成功");
        });
        addBtnListener(createSql, e ->{
            reLoadConfig();
            Container.coreService.createCustSql(this.sqlTables.getText(),this.sqlSqls.getText(),this.sqlPks.getText().toUpperCase());
            writeProperties();
            JOptionPane.showMessageDialog(null, "生成成功");
        });
        addBtnListener(openEx, e ->{
            try {
                Desktop.getDesktop().open(new File(filePanel.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        logBtn.addActionListener(e -> {
            showOrHidePanel(this.logPanel);
        });
        showSqlBtn.addActionListener(e ->{
            showOrHidePanel(this.sqlPanel);
        });

    }
    /**
     * 写入配置文件
     */
    public void writeProperties() {
        Properties pro = new Properties();
        FileOutputStream oFile;
        File tempFile = new File(PathConfig.configPath());
        pro.setProperty("SEQ_ON_LAST", PageConfig.SEQ_ON_LAST == null ? "false" : PageConfig.SEQ_ON_LAST.toString());
        pro.setProperty("URL", PageConfig.URL);
        pro.setProperty("USER", PageConfig.USER);
        pro.setProperty("PASSWORD", PageConfig.PASSWORD);
        pro.setProperty("WORK_SPACE", PageConfig.WORK_SPACE);
        pro.setProperty("appNo", PageConfig.appNo);
        pro.setProperty("tablesFromDB", PageConfig.tablesFromDB == null ? "" : StringUtils.join(PageConfig.tablesFromDB, ","));
        pro.setProperty("modelFiles", PageConfig.modelFiles == null ? "" : StringUtils.join(PageConfig.modelFiles, ","));
        try {
            if(!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();
            }
            oFile = new FileOutputStream(tempFile, false);
            pro.store(new OutputStreamWriter(oFile, "utf-8"), "Comment");
            oFile.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showOrHidePanel(JPanel panel){
        if(panel.isVisible()){
            hidePanel(panel);
        }else{
            showPanel(panel);
        }
    }
    public void hidePanel(JPanel panel){
        panel.setVisible(false);
        contentPanel.remove(panel);
        pack();
    }
    public void showPanel(JPanel panel){
        panel.setVisible(true);
        contentPanel.add(panel);
        pack();
    }

    private void setAllToolTip() {
        conBtn.setToolTipText("测试连接");
        runBtnExcel.setToolTipText("根据Excel生成代码");
        runBtnDb.setToolTipText("根据数据库生成代码");
        clearBtn.setToolTipText("清除工作空间");
        openEx.setToolTipText("打开工作空间");
        dbFile.setToolTipText("根据in文件夹里面的java类和数据库表名生成代码");
        showSqlBtn.setToolTipText("生成初始化脚本");
        logBtn.setToolTipText("日志信息");
    }


}
