package ui.Bstyle;

import java.awt.*;


/**
 * 本类专门存储项目中用到的所有颜色
 */
// final修饰防止被继承
public final class BColor {

    // 私有构造方法防止实例化
    private BColor() { }

    // 主题色:水鸭青
    public static final Color THEME_COLOR = new Color(1, 151, 136);

    // 提高亮度的主题色
    public static final Color LIGHT_THEME_COLOR = new Color(1, 177, 159);

    // 暗主题色
    public static final Color DARK_THEME_COLOR = new Color(1,113,102);
}
