package server.threads;

import java.io.*;
import java.net.Socket;

/**
 * 登录线程
 */
public class LoginThread extends Thread {
    private Socket socket;

    /**
     * 构造方法
     * @param socket 输入账号密码的socket
     */
    public LoginThread(Socket socket) {
        // 开启新线程
        this.socket = socket;
    }

    // 启动线程，响应客户端请求
    @Override
    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()
            ));

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()
            ));

            // 读取客户端传来的用户名
            String account_id = br.readLine();
            // 读取客户端传来的密码
            String password = br.readLine();

            // TODO 让用户名和密码比对


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
