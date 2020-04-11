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

    /**
     * 构造方法
     * @param client 输入账号密码的socket
     */
    public LoginThread(Socket client) {
        // 开启新线程
        this.client = client;
    }

    // 启动线程，响应客户端请求
    @Override
    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    client.getInputStream()
            ));

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    client.getOutputStream()
            ));
            PrintWriter pw = new PrintWriter(bw, true);

            // 读取客户端传来的用户名
            String account_id = br.readLine();
            // 读取客户端传来的密码
            String password = br.readLine();

            // TODO 让用户名和密码比对
            if(DbUtil.idMatch(account_id, password)) {

            }


        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
