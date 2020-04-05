package server;

/**
 * 数据库的通用工具类
 */
public class DbUtil {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/javatest?useSSL=false&serverTimezone=UTC";

    // static 语句块在类被加载的时候执行且仅会被执行一次，一般用来初始化静态变量和调用静态方法。
    static {
        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "20010806123";
    }
}
