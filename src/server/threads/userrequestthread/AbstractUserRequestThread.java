package server.threads.userrequestthread;

import server.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 抽象用户请求线程类，所有处理用户请求的线程类都继承与此类
 * @author Lian Guan
 */
public abstract class AbstractUserRequestThread extends Thread{

    /**
     * 发送请求的客户端
     */
    private Client client;

    /**
     * 构造方法
     * @param client 客户端socket
     */
    public AbstractUserRequestThread(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void run() {

    }
}
