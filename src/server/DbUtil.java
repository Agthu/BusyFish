package server;

import java.sql.*;

/**
 * 数据库的通用工具类
 */
public class DbUtil {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/busyfish?useSSL=false" +
                    "&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    // 数据库的用户名与密码
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
     * @return true代表注册成功，false代表注册失败
     */
    public static boolean createAccount(
            String account_id, String account_name, String account_password) throws SQLException {

        // 建立数据库连接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        // 具体的命令，在accounts这个表中添加一条数据
        String sql = "insert into accounts(account_id,account_name,account_password)" +
                " values(?,?,?) ";

        // PreparedStatement对象可以防止sql注入
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, account_id);
        ps.setString(2, account_name);
        ps.setString(3, account_password);

        try {
            // executeUpdate()函数执行增删改操作，返回更新的行数
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            return false;
        } finally {
            // 关闭连接，释放资源
            conn.close();
            ps.close();
        }
    }

    /**
     * 检测用户名和密码是否匹配
     * @param account_id 被检测的用户名
     * @param account_password 被检测的密码
     * @return true代表匹配，false代表不匹配(用户不存在或密码错误)
     */
    public static boolean idMatch(String account_id, String account_password) throws SQLException {
        // 取得连接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        // sql查询语句
        String sql = "select account_password from accounts where account_id=?";

        // 创建PreparedStatement对象
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, account_id);

        ResultSet result = null;
        String passwordQueried = null;
        try {
            // executeQuery()函数执行查询操作
            result = ps.executeQuery();
            // result指向最后一行（查询到的数据最多只有一行）
            result.last();
            if(result.getRow() == 1) { // 如果当前的行数为1
                passwordQueried = result.getString(1);
            }
            else return false; // 如果行数为0则账号不存在
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            ps.close();
            result.close();
        }
        return passwordQueried.equals(account_password);
    }

}
