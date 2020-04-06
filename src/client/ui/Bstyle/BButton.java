package client.ui.Bstyle;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 全局按钮样式
 */
public class BButton extends JButton implements MouseListener {

    /**
     * 构造方法
     * @param text 按钮上的文字
     */
    public BButton(String text) {
        super(text);

        // 去掉边框
        this.setBorderPainted(false);

        // 背景为主题色
        this.setBackground(BColor.THEME_COLOR);

        // 字体为白色
        this.setForeground(BColor.BRI_FONT_COLOR);

        // 去掉文字周围的焦点框
        this.setFocusPainted(false);

        // 添加事件监听器
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * 鼠标移动到上方时，颜色改为亮色
     * @param e 鼠标移动到上方事件
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(BColor.BRI_THEME_COLOR);
    }

    /**
     * 鼠标离开时，改回主题色
     * @param e 鼠标离开事件
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(BColor.THEME_COLOR);
    }
}
