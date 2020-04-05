package server.threads;

import java.net.Socket;

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

    }
}
