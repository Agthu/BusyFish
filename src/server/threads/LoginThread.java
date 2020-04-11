package server.threads;

import server.DbUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

/**
 * 登录线程
 */
public class LoginThread extends Thread {
    private Socket client;
    public static final String SUCCESS_MSG = "Login succeeded";
    public static final String FAIL_MSG = "Login failed";

    /**
     * 构造方法
     * @param client 客户端socket
     */
    public LoginThread(Socket client) {
        // 开启新线程
        this.client = client;
    }

    // 启动线程，响应客户端请求
    @Override
    public void run() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {

            br = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));

            bw = new BufferedWriter(new OutputStreamWriter(
                    client.getOutputStream()));

            pw = new PrintWriter(bw, true);

            // 读取客户端传来的用户名
            String account_id = br.readLine();
            // 读取客户端传来的密码
            String password = br.readLine();

            // 让用户名和密码比对
            if(DbUtil.idMatch(account_id, password)) {
                // 密码正确，则发送登录成功提示，并开启一个新线程
                pw.println(SUCCESS_MSG);
                new UserThread(client).start();
            } else {
                pw.println(FAIL_MSG);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            pw.println(FAIL_MSG);
        }
    }
}
