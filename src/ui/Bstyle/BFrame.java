package ui.Bstyle;

import javax.swing.*;
import com.mysql.jdbc.*;

import java.awt.*;

/**
 * 修改默认窗口样式
 * BXxx类都是重写JXxx类，本项目中控件的默认样式
 * 对于具体的控件，请不要直接实例化BXxx，而是先继承相应的类
 * @author Lian Guan
 */
public class BFrame extends JFrame {

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

        //设置背景色
        this.getContentPane().setBackground(Color.WHITE);
    }
}
