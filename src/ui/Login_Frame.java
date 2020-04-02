package ui;

import ui.Bstyle.BButton;
import ui.Bstyle.BFrame;
import ui.Bstyle.BPasswordField;
import ui.Bstyle.BTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * 登陆窗口
 * @see BFrame
 */
public class Login_Frame extends BFrame {

    // 账号和密码输入框、登陆按钮的引用
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login_Frame() {

        // 重命名窗口
        super("登陆");

        // 设置大小、位置
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        // 添加组件
        Container container  = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(idField = new Input_Id()); // 添加账号输入框
        container.add(passwordField = new Input_Password()); // 添加密码输入框
        container.add(loginButton = new Login_Button()); // 添加登陆按钮

        // 不可拖动窗口边缘来改变大小
        this.setResizable(false);

    }

    /**
     * 登陆按钮
     * @see BButton
     */
    class Login_Button extends BButton {
        public Login_Button() {
            super("登录");

            this.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO 登录功能实现
                }
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


