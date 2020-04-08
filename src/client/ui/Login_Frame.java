package client.ui;

import client.ui.Bstyle.BButton;
import client.ui.Bstyle.BFrame;
import client.ui.Bstyle.BPasswordField;
import client.ui.Bstyle.BTextField;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆窗口
 * @see BFrame
 */
public class Login_Frame extends BFrame {

    private JFrame loginFrame; // 登陆窗口的引用
    private Container container; // 窗口容器的引用
    private JTextField idField; // 账号输入框的引用
    private JPasswordField passwordField; // 密码输入框的引用
    private JButton loginButton; // 登陆按钮的引用
    private JButton registerButton; // 注册按钮的引用

    public Login_Frame() {
        // 重命名窗口
        super("登陆");

        loginFrame = this;

        // 设置大小、位置
        this.setSize(400, 300);
        // 居中显示
        this.setLocationRelativeTo(null);

        // 关闭后退出程序
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 添加组件
        container  = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel textFieldPanel = new JPanel(new FlowLayout());
        textFieldPanel.setSize(280, 50);
        textFieldPanel.add(idField = new Input_Id());
        textFieldPanel.add(passwordField = new Input_Password());
        container.add(textFieldPanel);
        container.add(loginButton = new Login_Button()); // 添加登陆按钮
        container.add(registerButton = new Register_Button());//添加注册按钮

        // 不可拖动窗口边缘来改变大小
        this.setResizable(false);

    }

    /**
     * 获取登录按钮
     * @return 登录按钮的引用
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    /**
     * 登陆按钮
     * @see BButton
     */
    class Login_Button extends BButton {
        Login_Button() {
            super("登录");

            this.addActionListener(e -> {
                // TODO 登录功能实现
            });

        }
    }

    /**
     * 注册按钮
     * @see BButton
     */
    class Register_Button extends BButton {
        Register_Button() {
            super("注册");

            this.addActionListener(e -> {
                new Register_Frame();
            });

        }
    }

}

/**
 * 账号输入框
 * @see BTextField
 */
class Input_Id extends BTextField {
    Input_Id() {
        super("请输入账号");
    }
}

/**
 * 密码输入框
 * @see BPasswordField
 */
class Input_Password extends BPasswordField {
    Input_Password() {
        super("请输入密码");
    }
}
