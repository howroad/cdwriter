package com.howroad.frame.panel;

import com.howroad.frame.jframe.ShowFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * <p>Title: JoinSqlLine.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 11:06
 */
public class JoinSqlLine extends JPanel{

    private TextPaneHalf tb1;
    private SelectPanel sel;
    private TextPaneHalf tb2;
    private JLabel on = new JLabel("on");
    private TextPaneHalf where;

    public JoinSqlLine(boolean enabled) {
        this.tb1 = new TextPaneHalf(ShowFrame.TEXT_LENGTH/2);
        this.sel = new SelectPanel(null, ShowFrame.TEXT_LENGTH/2,"这是提示信息", "LEFT JOIN ", "INNER JOIN ");
        this.tb2 = new TextPaneHalf(ShowFrame.TEXT_LENGTH/2);;
        this.on = new JLabel("ON");
        this.where = new TextPaneHalf(ShowFrame.TEXT_LENGTH);
        this.setLayout(new FlowLayout());
        this.add(tb1);
        this.add(sel);
        this.add(tb2);
        this.add(on);
        this.add(where);
        makeEnabled(enabled);
    }

    void makeEnabled(boolean enabled){
        this.tb1.makeEnabled(enabled);
    }

    public TextPaneHalf getTb1() {
        return tb1;
    }

    public void setTb1(TextPaneHalf tb1) {
        this.tb1 = tb1;
    }

    public SelectPanel getSel() {
        return sel;
    }

    public void setSel(SelectPanel sel) {
        this.sel = sel;
    }

    public TextPaneHalf getTb2() {
        return tb2;
    }

    public void setTb2(TextPaneHalf tb2) {
        this.tb2 = tb2;
    }

    public JLabel getOn() {
        return on;
    }

    public void setOn(JLabel on) {
        this.on = on;
    }

    public TextPaneHalf getWhere() {
        return where;
    }

    public void setWhere(TextPaneHalf where) {
        this.where = where;
    }
}
