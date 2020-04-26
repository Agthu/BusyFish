package client.ui.bstyle;

import javax.swing.*;
import java.awt.*;

/**
 * 设置全局默认文本框样式
 * @author Lian Guan
 */
public class BPasswordField extends JPasswordField {

    /**
     * <code>hintText</code> 是文本框中未输入文字时的提示内容，
     * 类似于占位符。
     */
    private String hintText;

    /**
     * <code>BTextField</code> 构造方法
     * @param hintText 提示性文字，类似于html的占位符
     */
    public BPasswordField(String hintText, int columns) {
        super(columns);
        this.hintText = hintText;

        // 设置字体大小
        this.setFont(new Font(
                this.getFont().getFontName(),
                Font.BOLD,
                BStandard.TEXT_FIELD_SIZE));
    }

    public BPasswordField(String hintText) {
        this(hintText, 10);
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

}

