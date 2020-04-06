package client.ui.Bstyle;

import java.awt.*;


/**
 * 本类专门存储项目中用到的所有颜色
 */
public final class BColor { // final修饰防止被继承

    // 私有构造方法防止实例化
    private BColor() { }

    // 主题色
    public static final Color THEME_COLOR = new Color(1, 151, 136);

    // 亮主题色
    public static final Color BRI_THEME_COLOR = new Color(1, 177, 159);

    // 暗主题色
    public static final Color DARK_THEME_COLOR = new Color(1,113,102);

    // 亮色字体，即在主题色之前显示的字体的颜色
    public static final Color BRI_FONT_COLOR = new Color(169, 220, 215);

    // 浅灰色，背景色
    public static final Color BG_LIGHT_GRAY = new Color(245, 245, 245);
}
