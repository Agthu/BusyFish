package server;

import server.threads.RegisterThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 注册服务类
 */
public class RegisterServer {
    private ServerSocket registerServerSocket;
    private Socket client;
    public static final int REGISTER_PORT = 9810; // 注册功能的端口

    public void startServer() throws IOException {
        registerServerSocket = new ServerSocket(REGISTER_PORT);

        while(true) {
            client = registerServerSocket.accept();
            new RegisterThread(client).start();
        }
    }
}
