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
        this.socket = socket;
        System.out.println("开启新线程");
    }

    //启动线程，响应客户端请求
    @Override
    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()
            ));
            System.out.println("创建br对象");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()
            ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
