package server.threads;

import client.data.User;
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

        OutputStream os;
        PrintWriter pw;
        try {
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // 获取客户端传来的User对象
            User user = (User)ois.readObject();
            // 关闭输入流
            socket.shutdownInput();

            os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // 如果创建成功，则向客户端发送成功提示
            if(DbUtil.createAccount(user.getId(), user.getName(), user.getPassword())) {

            }
            else {

            }
            
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
