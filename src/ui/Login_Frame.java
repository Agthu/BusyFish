package ui;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆窗口
 * @see BFrame
 */
public class Login_Frame extends BFrame {
    public Login_Frame() {

        // 用super的方式重命名窗口
        super("登陆");

        //设置大小、位置
        this.setBounds(500, 500, 800, 600);

        //添加组件
        Container container  = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new Input_Id());
        container.add(new Input_Password());

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
class Input_Password extends JPasswordField {
    Input_Password() {
        super("请输入密码");
    }
}

/*登陆按钮*/
class Login_Button extends JButton {
    public Login_Button() {

    }
}