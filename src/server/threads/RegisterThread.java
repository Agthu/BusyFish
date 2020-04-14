package server.threads;

import data.Hint;
import data.User;
import server.DbUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

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

        OutputStream os;
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
