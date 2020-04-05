package server;

import java.sql.*;

/**
 * 数据库的通用工具类
 */
public class DbUtil {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/busyfish?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "root";
    private static final String PASSWORD = "20010806123";

    // static 语句块在类被加载的时候执行且仅会被执行一次，一般用来初始化静态变量和调用静态方法。
    static {
        // 注册jdbc驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册账号
     * @param account_id 用户名
     * @param account_name 昵称
     * @param account_password 密码
     */
    public static void createAccount(
            String account_id, String account_name, String account_password) throws SQLException {

        // 建立数据库连接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        // 具体的命令
        String sql = "insert into accounts(account_id,account_name,account_password)" +
                " values(?,?,?) ";

        // PreparedStatement对象可以防止sql注入
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, account_id);
        ps.setString(2, account_name);
        ps.setString(3, account_password);

        try {
            // executeUpdate()函数执行增删改操作，返回更新的行数
            int rowsChanged = ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接，释放资源
            conn.close();
            ps.close();
        }
    }

}
