package com.howroad.cdwriter.control;

import com.howroad.frame.jframe.ShowFrame;
import org.junit.jupiter.api.Test;

/**
 * <p>Title: Start.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since 2019-10-31 15:20
 */
public class Start {
    public static void main(String[] args) {
        ShowFrame showFrame = new ShowFrame();
        showFrame.setVisible(true);
    }

    @Test
    public void test(){
        ShowFrame showFrame = new ShowFrame();
        showFrame.setVisible(true);
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
