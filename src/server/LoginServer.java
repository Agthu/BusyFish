package server;

import server.threads.LoginThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 登录服务类，提供登录服务
 * @author Lian Guan
 */
public class LoginServer {
    /**
     * 登录功能的端口
     */
    public static final int LOGIN_PORT = 9811;
    private ServerSocket loginServerSocket;

    /**
     * 启动服务
     * @throws IOException IOException
     */
    public void startServer() throws IOException {
        loginServerSocket = new ServerSocket(LOGIN_PORT);

        while(true) {
            // 获取客户端的socket
            Socket client = loginServerSocket.accept();
            // 开启新线程来提供登录服务
            new LoginThread(client).start();
        }
    }

}
