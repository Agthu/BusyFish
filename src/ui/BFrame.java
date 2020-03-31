package ui;

import javax.swing.*;
import com.mysql.jdbc.*;

import java.awt.*;

/**
 * 修改默认窗口样式
 * @author Lian Guan
 */
public abstract class BFrame extends JFrame {

    /**
     * 构造方法
     * 不传入任何参数
     */
    public BFrame() {
        this("");
    }
    /**
     * 构造方法
     * @param title 标题
     */
    public BFrame(String title) {
        super(title);

        this.setBackground(Color.WHITE);
        this.getContentPane().setBackground(Color.RED);
    }
}
