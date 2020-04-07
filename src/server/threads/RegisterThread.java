package server.threads;

import server.DbUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class RegisterThread extends Thread {

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

            // 注册成功/失败时，向客户端发送提示信息
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
