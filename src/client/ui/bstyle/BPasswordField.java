package client.ui.bstyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 设置全局默认文本框样式
 * @author Lian Guan
 */
public class BPasswordField extends JPasswordField implements FocusListener {

    /**
     * <code>hintText</code> 是文本框中未输入文字时的提示内容，
     * 类似于占位符。
     */
    private String hintText;

    /**
     * <code>BTextField</code> 构造方法
     * @param hintText 提示性文字，类似于html的占位符
     */
    public BPasswordField(String hintText) {

        this.hintText = hintText;


        // 设置字体大小
        this.setFont(new Font(
                this.getFont().getFontName(),
                Font.BOLD,
                BStandard.TEXT_FIELD_SIZE));
    }

    @Override
    protected void paintComponent(Graphics pG) {
        super.paintComponent(pG);
        if(hintText == null || hintText.length() == 0 || getPassword().length > 0) {
            return;
        }
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setColor(getDisabledTextColor());
        g.drawString(hintText, getInsets().left, pG.getFontMetrics().getMaxAscent());
    }

    /**
     * 获得焦点时，提示文本清空并把密码显示为星号，把
     * @param e 获得焦点事件
     */
    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().equals(hintText)) {
            this.setText("");
            this.setEchoChar('*');
            this.setForeground(Color.black);
        }
    }


    /**
     * 失去焦点时，如果未输入密码，则恢复占位符
     * @param e 失去焦点事件
     */
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().equals("")) {
            this.setEchoChar((char)0);
            this.setText(hintText);
            this.setForeground(Color.GRAY);
        }
    }
}

