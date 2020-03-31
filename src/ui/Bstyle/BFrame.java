package ui.Bstyle;

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

        //设置关闭事件：退出程序
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //设置背景色
        this.getContentPane().setBackground(Color.WHITE);
    }
}
