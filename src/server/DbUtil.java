/*
数据库名：busyfish
所有的数据表：
    表名：accounts 字段名/类型/描述：
        account_id / VARCHAR(30) / 用户名（主键）
        account_name / VARCHAR(50) / 昵称
        account_password / VARCHAR(30) / 密码

    表名：products 字段名/类型/描述
        product_id / INT / 商品编号(自动增长,)
        product_name / VARCHAR(50) / 商品名称
        publisher_id / VARCHAR(30) / 发布者id
        product_description / VARCHAR(300) / 商品描述
        product_price / DECIMAL(10,2) / 价格
        bought / tinyint / 是否已被购买

    表名：purchase_history 字段名/类型/描述
        record_id / INT / 记录编号
        buyer_id / VARCHAR(30) / 买家id
        product_id / INT / 商品编号
 */

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
    public static boolean createAccount(String account_id, String account_name,
                                        String account_password) throws SQLException {

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
    public static boolean idMatch(
            String account_id, String account_password) throws SQLException {
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

    /**
     * 发布商品
     * @param product_name 商品名
     * @param publisher_id 发布者id
     * @param description 描述
     * @param price 价格
     * @return true代表发布成功，false代表发布失败
     */
    public static boolean addProduct(String product_name, String publisher_id,
                                     String description, double price) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        // 插入语句
        String sql = "insert into products values(null,?,?,?,?,0)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, product_name); // 商品名称
        ps.setString(2, publisher_id); // 发布者id
        ps.setString(3, description); // 商品描述
        ps.setDouble(4, price); // 价格

        try {
            ps.executeUpdate();
            return true; // 添加成功时返回true
        } catch(SQLException e) {
            return false; // 失败时返回false
        } finally {
            conn.close();
            ps.close();
        }
    }

    /**
     * 购买商品
     * @param buyer_id 购买者的id
     * @param product_id 商品id
     * @return true代表购买成功，false代表购买失败
     */
    public static boolean buyProduct(String buyer_id, int product_id)
            throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        // 查询语句，查询商品是否已经被购买的命令
        String queryCommand = "SELECT bought FROM products WHERE product_id=?";
        // 将商品设置为已经被购买的命令
        String updateCommand = "UPDATE products SET bought=1 WHERE product_id=?";
        // 插入一条购买记录的命令
        String insertCommand = "INSERT INTO purchase_history values(NULL,?,?)";

        PreparedStatement ps = conn.prepareStatement(queryCommand);
        ps.setInt(1, product_id);
        ResultSet result = null;

        try {
            // 先查询商品是否已经被购买，如果是，返回false，
            result = ps.executeQuery();
            result.last();
            int bought = result.getInt(1);
            if(bought == 1) {
                return false;
            }
            else {
                // 否则将bought属性设置为1，并添加一条购买记录
                ps = conn.prepareStatement(updateCommand);
                ps.setInt(1,product_id);
                ps.executeUpdate();

                ps = conn.prepareStatement(insertCommand);
                ps.setString(1, buyer_id);
                ps.setInt(2, product_id);
                ps.executeUpdate();
                return true;
            }
        } catch(SQLException e) {
            return false;
        } finally {
            conn.close();
            result.close();
            ps.close();
        }
    }

    /**
     * 删除商品
     * @param product_id 被删除的商品id
     * @return true代表删除成功，false代表失败
     */
    public static boolean deleteProduct(int product_id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        String sql = "delete from products where product_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, product_id);
        try {
            ps.executeUpdate(); // 执行删除命令
            return true;
        } catch(SQLException e) {
            return false;
        } finally {
            conn.close();
            ps.close();
        }

    }
}
