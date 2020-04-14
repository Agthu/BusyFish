package client.ui.bstyle;

import javax.swing.border.Border;
import java.awt.*;

/**
 * 圆角边框
 * @author Lian Guan
 */
public class BRoundBorder implements Border {

    /**
     * 边框颜色
     */
    private Color color;

    private int arcH = 15;
    private int arcW = 15;

    /**
     * 没有传值，默认是黑色边框
     */
    public BRoundBorder() {
        this(Color.BLACK);
    }

    /**
     * @param color 边框颜色
     */
    public BRoundBorder(Color color) {
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, arcH, arcW);

        g2d.dispose();
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(10, 15, 10, 15);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
