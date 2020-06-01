package server.threads;

import server.Client;
import server.DbUtil;
import data.Hint;
import data.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

/**
 * 登录线程
 * @author Lian Guan
 */
public class LoginThread extends Thread {
    private final Socket client;
    /**
     * 成功提示信息
     */
    public static final Hint SUCCESS_HINT = new Hint(true);

    /**
     * 失败提示信息
     */
    public static final Hint FAIL_HINT = new Hint(false);

    /**
     * 构造方法
     * @param client 客户端socket
     */
    public LoginThread(Socket client) {
        // 开启新线程
        this.client = client;
    }

    /**
     * 启动线程，相应客户端需求
     */
    @Override
    public void run() {

        try {
            // 先创建输出流对象，再创建输入流对象，否则会报错
            ObjectOutputStream oos = new ObjectOutputStream(
                    client.getOutputStream()
            );
            ObjectInputStream ois = new ObjectInputStream(
                    client.getInputStream()
            );

            // 读取客户端传来的用户信息
            User user = (User)ois.readObject();
            client.shutdownInput();

            // 让用户名和密码比对
            if(DbUtil.idMatch(user.getId(), user.getPassword())) {

                // 密码正确，则发送登录成功提示，并开启一个新线程
                oos.writeObject(SUCCESS_HINT);
                new UserThread(new Client(client, user.getId(), oos, ois)).start();

            } else {
                oos.writeObject(FAIL_HINT);
            }
            client.shutdownOutput();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
