package client.ui;

import client.ui.bstyle.BFrame;
import client.ui.bstyle.BTextField;

import java.awt.*;

/**
 * 注册窗口
 * @author Lian Guan
 */
public class RegisterFrame extends BFrame {
    public RegisterFrame() {
        // 设置标题
        super("注册");
        // 设置大小
        this.setSize(300, 200);
        // 窗口居中
        this.setLocationRelativeTo(null);
        Container container = this.getContentPane();
        container.add(new BTextField("请输入账号"));
        this.setVisible(true);
    }
}
