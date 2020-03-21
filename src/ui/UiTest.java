package ui;

import javax.swing.*;
import java.awt.*;

public class UiTest {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setBounds(500, 500, 600, 400);
        Container c = jf.getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new BTextField("请输入账号"));
        c.add(new BTextField("请输入密码"));

        jf.setVisible(true);
    }
}
