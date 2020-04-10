package server.threads;

import server.DbUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class RegisterThread extends Thread {

    private Socket socket;
    public static final String SUCCESS_MSG = "Register succeeded"; // 成功的提示信息
    public static final String FAIL_MSG = "Register failed"; // 失败的提示信息

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

            // 注册成功/失败时，向客户端发送提示信息
            if(DbUtil.createAccount(id, name, password)) {
                writer.println(SUCCESS_MSG);
            } else {
                writer.println(FAIL_MSG);
            }

            socket.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
