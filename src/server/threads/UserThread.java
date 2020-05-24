package server.threads;

import data.Product;
import data.Request;
import server.threads.userrequestthread.AddProThread;
import server.threads.userrequestthread.SendProByIdThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 正式服务线程，处理一系列用户请求
 * @author Lian Guan
 */
public class UserThread extends Thread {
    private final Socket client;

    /**
     * 当前服务的客户端的用户ID
     */
    private String accountId;

    /**
     * 构造方法
     * @param client 客户端的socket
     * @param accountId 当前客户端用户的id
     */
    public UserThread(Socket client, String accountId) {
        this.client = client;
        this.accountId = accountId;
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

                // 判断请求类型
                Request.RequestType requestType = request.getRequestType();
                switch(requestType) {
                    case GET_PRO_BY_ID:
                        Product product = (Product)ois.readObject();
                        new SendProByIdThread(client, product.getId()).start();
                        break;

                    case ADD_PRO:
                        Product detailedPro = (Product)ois.readObject();
                        new AddProThread(client, detailedPro, accountId).start();
                        break;

                    case GET_NEW_PRO:


                    default:
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
