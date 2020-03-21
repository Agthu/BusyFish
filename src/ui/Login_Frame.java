package ui;

import javax.swing.*;
import java.awt.*;

public class Login_Frame extends JFrame {
    public Login_Frame() {

        // 用super的方式重命名窗口
        super("登陆");

        //设置大小、位置
        this.setBounds(500, 500, 800, 600);
        Container container  = this.getContentPane();

    }
}

/*账号输入框*/
class Input_Id extends BTextField {
    public Input_Id() {
        super("请输入账号");
    }
}

/*密码输入框*/
class Input_Password extends JPasswordField {
    public Input_Password() {

    }
}

/*登陆按钮*/
class Login_Button extends JButton {
    public Login_Button() {

    }
}