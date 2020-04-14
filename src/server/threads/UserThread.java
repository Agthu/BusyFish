package server.threads;

import data.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        while(true) {
            try {
                // 接收一个请求对象
                Request request = (Request)ois.readObject();

                // TODO 判断请求类型
                Request.RequestType requestType = request.getRequestType();
                switch(requestType) {
                    case GET_PRO_BY_ID:

                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
