/*
数据库名：busyfish
所有的数据表：
    表名：accounts
        字段名/类型/描述：
        account_id / VARCHAR(30) / 用户名（主键）
        account_name / VARCHAR(50) / 昵称
        account_password / VARCHAR(30) / 密码

    表名：products
        字段名/类型/描述
        product_id / INT / 商品编号(自动增长,)
        product_name / VARCHAR(50) / 商品名称
        publisher_id / VARCHAR(30) / 发布者id
        product_description / VARCHAR(300) / 商品描述
        product_price / DECIMAL(10,2) / 价格
        bought / tinyint / 是否已被购买

    表名：purchase_history
        字段名/类型/描述
        record_id / INT / 记录编号
        buyer_id / VARCHAR(30) / 买家id
        product_id / INT / 商品编号

    表名：message
 */

package server;

import data.Comment;
import data.Product;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 数据库的通用工具类
 */
public class DbUtil {

    /**
     * @var JDBC_DRIVER
     */
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/busyfish?useSSL=false" +
                    "&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    /**
     * 数据库的用户名和密码
     */
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
     * 获取数据库连接
     * @return 数据库连接
     * @throws SQLException 连接异常
     */
    private static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    /**
     * 注册账号
     * @param accountId 用户名
     * @param accountName 昵称
     * @param accountPassword 密码
     * @return true代表注册成功，false代表注册失败
     */
    public static boolean createAccount(String accountId, String accountName,
                                        String accountPassword) throws SQLException {

        // 建立数据库连接
        Connection database = getDatabaseConnection();

        // 具体的命令，在accounts这个表中添加一条数据
        String sql = "insert into accounts(account_id,account_name,account_password)" +
                " values(?,?,?) ";

        // PreparedStatement对象可以防止sql注入
        PreparedStatement ps = database.prepareStatement(sql);
        ps.setString(1, accountId);
        ps.setString(2, accountName);
        ps.setString(3, accountPassword);

        try {
            // executeUpdate()函数执行增删改操作，返回更新的行数
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            return false;
        } finally {
            // 关闭连接，释放资源
            database.close();
            ps.close();
        }
    }

    /**
     * 检测用户名和密码是否匹配
     * @param accountId 被检测的用户名
     * @param accountPassword 被检测的密码
     * @return true代表匹配，false代表不匹配(用户不存在或密码错误)
     */
    public static boolean idMatch(
            String accountId, String accountPassword) throws SQLException {
        // 取得连接
        Connection database = getDatabaseConnection();

        // sql查询语句
        String sql = "select account_password from accounts where account_id=?";

        // 创建PreparedStatement对象
        PreparedStatement ps = database.prepareStatement(sql);
        ps.setString(1, accountId);

        ResultSet result;
        // 查询到的正确密码
        String passwordQueried = null;
        try {
            // executeQuery()函数执行查询操作
            result = ps.executeQuery();

            // result指向最后一行（查询到的数据最多只有一行）
            result.last();

            // 如果当前的行数为1
            if(result.getRow() == 1) {
                passwordQueried = result.getString(1);
            }
            else {
                // 如果行数为0则账号不存在
                return false;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();
            ps.close();
        }
        return passwordQueried.equals(accountPassword);
    }

    /**
     * 发布商品
     * @param productName 商品名
     * @param publisherId 发布者id
     * @param description 描述
     * @param price 价格
     * @return true代表发布成功，false代表发布失败
     */
    public static boolean addProduct(String productName, String publisherId,
                                     String description, double price) throws SQLException {
        Connection database = getDatabaseConnection();

        // 插入语句
        String sql = "insert into products values(null,?,?,?,?,0)";
        PreparedStatement ps = database.prepareStatement(sql);
        ps.setString(1, productName);
        ps.setString(2, publisherId);
        ps.setString(3, description);
        ps.setDouble(4, price);

        try {
            ps.executeUpdate();
            // 添加成功时返回true
            return true;
        } catch(SQLException e) {
            // 失败时返回false
            return false;
        } finally {
            database.close();
            ps.close();
        }
    }


    /**
     * 购买商品
     * @param buyerId 购买者的id
     * @param productId 商品id
     * @return true代表购买成功，false代表购买失败
     */
    public static boolean buyProduct(String buyerId, int productId)
            throws SQLException {
        Connection database = getDatabaseConnection();

        // 查询语句，查询商品是否已经被购买的命令
        String queryCommand = "SELECT bought FROM products WHERE product_id=?";
        // 将商品设置为已经被购买的命令
        String updateCommand = "UPDATE products SET bought=1 WHERE product_id=?";
        // 插入一条购买记录的命令
        String insertCommand = "INSERT INTO purchase_history values(NULL,?,?)";

        PreparedStatement ps = database.prepareStatement(queryCommand);
        ps.setInt(1, productId);
        ResultSet result = null;

        try {
            // 先查询商品是否已经被购买，如果是，返回false，
            result = ps.executeQuery();
            // 使result指向最后一行（一开始并不指向任何一行）
            result.last();
            int bought = result.getInt(1);
            if(bought == 1) {
                return false;
            }
            else {
                // 否则将bought属性设置为1，并添加一条购买记录
                ps = database.prepareStatement(updateCommand);
                ps.setInt(1,productId);
                ps.executeUpdate();

                ps = database.prepareStatement(insertCommand);
                ps.setString(1, buyerId);
                ps.setInt(2, productId);
                ps.executeUpdate();
                return true;
            }
        } catch(SQLException e) {
            return false;
        } finally {
            database.close();
            ps.close();
        }
    }

    /**
     * 删除商品
     * @param productId 被删除的商品id
     * @return true代表删除成功，false代表失败
     */
    public static boolean deleteProduct(int productId) throws SQLException {
        Connection database = getDatabaseConnection();

        String sql = "delete from products where product_id=?";
        PreparedStatement ps = database.prepareStatement(sql);
        ps.setInt(1, productId);
        try {
            // 执行删除命令
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            return false;
        } finally {
            database.close();
            ps.close();
        }

    }

    /**
     * 根据id获取商品信息
     * @param id 商品id
     * @return 商品信息
     */
    public static Product getProductById(int id) throws SQLException {
        Connection database = getDatabaseConnection();

        // 数据库查询语句
        String sql = "SELECT * FROM products WHERE product_id=?";
        PreparedStatement ps = database.prepareStatement(sql);
        ps.setInt(1, id);

        // 查询结果的引用
        ResultSet result = null;
        try {
            result = ps.executeQuery();
            result.last();
            return new Product(
                    result.getInt("product_id"),
                    result.getString("product_name"),
                    result.getString("publisher_id"),
                    result.getString("product_description"),
                    result.getDouble("product_price"),
                    result.getInt("bought")
            );
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
            ps.close();
        }
    }

    /**
     * 获取最新商品
     * @param num 最大商品总数
     * @return 最新商品的链表
     */
    public static LinkedList<Product> getNewProducts(int num) throws SQLException {
        Connection database = getDatabaseConnection();

        // 存放商品信息的数据结构
        LinkedList<Product> proList = new LinkedList<>();

        // 查询语句，查询前n条记录
        String queryCommand = "SELECT * FROM products WHERE bought=0 " +
                "LIMIT ?,?";
        PreparedStatement ps = database.prepareStatement(queryCommand);
        ps.setInt(1, 0);
        ps.setInt(2, num);

        ResultSet result;

        try {
            result = ps.executeQuery();

            // 遍历结果集中的所有记录
            while(result.next()) {
                // 根据记录向proList中添加一个新的商品
                proList.add(new Product(
                        result.getInt("product_id"),
                        result.getString("product_name"),
                        result.getString("publisher_id"),
                        result.getString("product_description"),
                        result.getDouble("product_price"),
                        result.getInt("bought")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
            ps.close();
        }
        return proList;
    }

    /**
     * 根据商品id获取评论
     * @param productId 商品id
     * @return 评论的链表
     */
    public static LinkedList<Comment> getCommentOf(int productId) throws SQLException {
        Connection database = getDatabaseConnection();

        LinkedList<Comment> commentList = new LinkedList<>();

        String queryCommand = "SELECT * FROM comments WHERE product_id=?";
        PreparedStatement ps = database.prepareStatement(queryCommand);
        ps.setInt(1, productId);

        ResultSet result;

        result = ps.executeQuery();

        while(result.next()) {
            commentList.add(new Comment(
                    result.getInt("comment_id"),
                    result.getString("publisher_id"),
                    result.getString("content")
            ));
        }
        database.close();
        return commentList;
    }

    /**
     * 发布评论
     * @param productId 商品id
     * @param publisherId 发布者id
     * @param content 评论内容
     * @return 成功或失败
     */
    public static boolean AddComment(int productId, String publisherId, String content) throws SQLException {
        Connection database = getDatabaseConnection();
        String updateCommand = "INSERT INTO comments(comment_id,product_id,publisher_id,content) values(null,?,?,?)";
        PreparedStatement ps = database.prepareStatement(updateCommand);
        ps.setInt(1, productId);
        ps.setString(2, publisherId);
        ps.setString(3, content);
        try {
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            return false;
        } finally {
            database.close();
        }
    }

    /**
     * 发送消息
     * @param from 发送者
     * @param to 接受者
     * @param content 消息内容
     * @return 成功或失败
     */
    public static boolean sendMessage(String from, String to, String content) throws SQLException {
        Connection database = getDatabaseConnection();
        String updateCommand = "INSERT INTO message(idmessage,from,to,content) values(null,?,?,?)";

        PreparedStatement ps = database.prepareStatement(updateCommand);

        ps.setString(1, from);
        ps.setString(2, to);
        ps.setString(3, content);

        try {
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            return false;
        } finally {
            database.close();
        }
    }
}
