package client.ui.bstyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 设置全局默认文本框样式
 * @author Lian Guan
 */
public class BTextField extends JTextField {

    /**
     * <code>hintText</code> 是文本框中未输入文字时的提示内容，
     * 类似于占位符。
     */
    private String hintText;

    /**
     * <code>BTextField</code> 构造方法
     * @param hintText 提示性文字，类似于html的占位符
     */
    public BTextField(String hintText) {
        this.hintText = hintText;
        this.setSize(400, 10);
//        // 设置默认文本为hintText
//        this.setText(this.hintText);

//        // 提示文本为灰色
//        this.setForeground(Color.GRAY);
//
//        // 添加监听器：获得焦点的监听器
//        this.addFocusListener(this);

        // 设置文字大小
        this.setFont(new Font(
                this.getFont().getFontName(),
                Font.BOLD,
                BStandard.TEXT_FIELD_SIZE));
    }

//    /**
//     * 获取焦点时，清空提示文本内容并把字体颜色设为黑色
//     * @param e 获取焦点事件
//     */
//    @Override
//    public void focusGained(FocusEvent e) {
//        String text = this.getText();
//
//        // 当文本框内的内容与hintText不相同时，认为用户还未输入，自动清空
//        if(text.equals(hintText)) {
//            this.setText("");
//            this.setForeground(Color.BLACK);
//        }
//    }
//
//    /**
//     * 失去焦点时，如果文本框内容为空，恢复灰色的提示性文字
//     * @param e 失去焦点事件
//     */
//    @Override
//    public void focusLost(FocusEvent e) {
//        if(this.getText().equals("")) {
//            this.setForeground(Color.GRAY);
//            this.setText(hintText);
//        }
//    }


    @Override
    protected void paintComponent(Graphics pG) {
        super.paintComponent(pG);
        if(hintText == null || hintText.length() == 0 || getText().length() > 0) {
            return;
        }
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setColor(getDisabledTextColor());
        g.drawString(hintText, getInsets().left, pG.getFontMetrics().getMaxAscent()+getInsets().top);
    }
}
