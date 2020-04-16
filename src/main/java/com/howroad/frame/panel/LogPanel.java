package com.howroad.frame.panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


/**
 * <p>Title: LogFrame.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * @since：20190904
 */
public class LogPanel extends JPanel {

    private static final long serialVersionUID = 7178981797120622698L;

    private JTextArea jTextArea = new JTextArea(null,"[log]",20,60);

    private JButton button = new JButton("clr");

    private JScrollPane jScrollPane = new JScrollPane(jTextArea);

    private JPanel buttonPanel = new JPanel();

    private OutputStream outputStream;

    public LogPanel() {

        //分别设置水平和垂直滚动条自动出现
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //分别设置水平和垂直滚动条总是出现
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                jTextArea.append(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                jTextArea.append(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        buttonPanel.add(button);

        this.add(jScrollPane);
        this.add(buttonPanel);

        System.setOut(new PrintStream(outputStream, true));
        System.setErr(new PrintStream(outputStream, true));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
            }
        });
    }

    public void log(String log){
        jTextArea.append(log + "\r\n");
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
