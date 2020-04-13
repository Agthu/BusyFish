package server.threads;

import java.net.Socket;

/**
 * 正式服务线程，处理一系列用户请求
 */
public class UserThread extends Thread {
    private Socket client;
    private String account_id;

    /**
     * 构造方法
     * @param client 客户端的socket
     * @param account_id 当前客户端用户的id
     */
    public UserThread(Socket client, String account_id) {
        this.client = client;
        this.account_id = account_id;
    }

    @Override
    public void run() {

    }
}
