package server;

import server.threads.RegisterThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 注册服务类
 * @author Lian Guan
 */
public class RegisterServer {

    /**
     * 用于接收客户端请求的ServerSocket
     */
    private ServerSocket registerServerSocket;

    /**
     * 客户端的连接
     */
    private Socket client;

    /**
     * 注册功能的端口
     */
    public static final int REGISTER_PORT = 9810;

    public void startServer() throws IOException {
        registerServerSocket = new ServerSocket(REGISTER_PORT);

        while(true) {
            // 接收一个客户端连接
            client = registerServerSocket.accept();
            // 开启一个新线程，用于注册
            new RegisterThread(client).start();
        }
    }
}
