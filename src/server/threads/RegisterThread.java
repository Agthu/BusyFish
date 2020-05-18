package server.threads;

import data.Hint;
import data.User;
import server.DbUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

/**
 * 注册线程，用于处理一个客户端的注册请求
 * @author Lian Guan
 */
public class RegisterThread extends Thread {

    private Socket socket;

    /**
     * 成功提示信息
     */
    public static final Hint SUCCESS_HINT = new Hint(true);

    /**
     * 失败提示信息
     */
    public static final Hint FAIL_HINT = new Hint(false);

    public RegisterThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream()
            );
            ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream()
            );

            // 获取客户端传来的User对象
            User user = (User)ois.readObject();

            // 如果创建成功，则向客户端发送成功提示
            if(DbUtil.createAccount(user.getId(), user.getName(), user.getPassword())) {
                oos.writeObject(SUCCESS_HINT);
            }
            else {
                oos.writeObject(FAIL_HINT);
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
