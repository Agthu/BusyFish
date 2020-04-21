package client.ui;

import data.Hint;
import data.User;
import client.ui.bstyle.BButton;
import client.ui.bstyle.BFrame;
import client.ui.bstyle.BPasswordField;
import client.ui.bstyle.BTextField;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 登陆窗口
 * @author Lian Guan
 * @see BFrame
 */
public class LoginFrame extends BFrame {

    /**
     * 登录窗口的引用
     */
    private JFrame loginFrame;

    /**
     * 窗口容器的引用
     */
    private Container container;

    /**
     * 账号输入框的引用
     */
    private JTextField idField;

    /**
     * 密码输入框的引用
     */
    private JPasswordField passwordField;

    /**
     * 登陆按钮的引用
     */
    private JButton loginButton;

    /**
     * 注册按钮的引用
     */
    private JButton registerButton;

    public static final String HOST = "127.0.0.1";
    public static final int LOGIN_PORT = 9811;

    public LoginFrame() {
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
        textFieldPanel.add(idField = new InputId());
        textFieldPanel.add(passwordField = new InputPassword());
        container.add(textFieldPanel);

        // 添加登录按钮
        container.add(loginButton = new LoginButton());

        // 添加注册按钮
        container.add(registerButton = new RegisterButton());

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
     * 获取注册按钮
     * @return 注册按钮的引用
     */
    public JButton getRegisterButton() {
        return registerButton;
    }

    /**
     * 登陆按钮
     * @see BButton
     */
    class LoginButton extends BButton {
        LoginButton() {
            super("登录");

            this.addActionListener(e -> {
                // 登录功能实现
                try {
                    // 建立客户端socket
                    Socket socket = new Socket(HOST, LOGIN_PORT);

                    // 获取成功提示的对象输入流
                    ObjectInputStream ois = new ObjectInputStream(
                            socket.getInputStream());

                    // 用于发送用户名的密码的对象输出流
                    ObjectOutputStream oos = new ObjectOutputStream(
                            socket.getOutputStream());

                    // 发送登录请求和用户名密码信息
                    oos.writeObject(new User(idField.getText(), passwordField.getText()));

                    // 获取登录成功的提示
                    Hint hint = (Hint)ois.readObject();
                    if(hint.isSuccess()) {
                        System.out.println("成功");
                        // TODO 进入主界面
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            });

        }
    }

    /**
     * 注册按钮
     * @see BButton
     */
    class RegisterButton extends BButton {
        RegisterButton() {
            super("注册");

            this.addActionListener(e -> {
                new RegisterFrame();
            });

        }
    }

}

/**
 * 账号输入框
 * @see BTextField
 */
class InputId extends BTextField {
    InputId() {
        super("请输入账号");
    }
}

/**
 * 密码输入框
 * @see BPasswordField
 */
class InputPassword extends BPasswordField {
    InputPassword() {
        super("请输入密码");
    }
}
