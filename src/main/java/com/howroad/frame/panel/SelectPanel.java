package com.howroad.frame.panel;

import org.apache.commons.lang3.StringUtils;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * <p>Title: MyPanel.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年3月6日 下午1:53:29
 * 
 */
public class SelectPanel extends JPanel{
    private static final long serialVersionUID = 679208663304422938L;
    private JLabel label;
    private JComboBox<String> comboBox;
    public SelectPanel(String lable,int length, String tips, String...items) {
        super();
        if(!StringUtils.isBlank(lable)){
            label = new JLabel(lable);
            label.setPreferredSize(new Dimension(2 * length, 20));
            label.setToolTipText(tips);
            this.add(label);
        }
        comboBox = new JComboBox<String>();
        comboBox.setPreferredSize(new Dimension(10 * length, 20));
        for (String string : items) {
            comboBox.addItem(string);
        }
        comboBox.setToolTipText(tips);
        this.add(comboBox);
    }
    public String getText() {
        return ((String) comboBox.getSelectedItem()).trim();
    }

    public void setText(String item){
        comboBox.setSelectedItem(item);
    }
}
