package server.threads;

import server.DbUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class RegisterThread extends Thread {

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/busyfish?useSSL=false&serverTimezone=UTC";

    private Socket socket;

    public RegisterThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 接受来自客户端的信息
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // 用于发送注册成功信息
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // 接收用户名、昵称、密码
            String id = reader.readLine();
            String name = reader.readLine();
            String password = reader.readLine();

            // 创建成功/失败时，向客户端发送提示信息
            if(DbUtil.createAccount(id, name, password)) {
                writer.println("Register succeeded");
            } else {
                writer.println("Register failed");
            }

            socket.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
