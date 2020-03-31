package ui.Bstyle;

import javax.swing.*;
import java.awt.*;

/**
 * 全局按钮样式
 */
public class BButton extends JButton {

    /**
     * 构造方法
     * @param text 按钮上的文字
     */
    public BButton(String text) {
        super(text);
        this.setBorderPainted(false);
        this.setBackground(Color.getColor("#4285F4"));
    }
}
