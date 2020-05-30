package server.threads;

import data.Product;
import data.Request;
import server.Client;
import server.threads.userrequestthread.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * 正式服务线程，处理一系列用户请求
 * @author Lian Guan
 */
public class UserThread extends Thread {
    private final Client client;


    /**
     * 构造方法
     * @param client 客户端的socket
     */
    public UserThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        oos = client.getOos();
        ois = client.getOis();


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
                        new AddProThread(client, detailedPro, client.getUserId()).start();
                        break;

                    case GET_NEW_PRO:
                        new GetNewProThread(client).start();
                        break;

                    case GET_COMMENT_BY_ID:
                        new GetCommentByIdThread(client).start();
                        break;

                    case ADD_COMMENT:
                        new AddCommentThread(client).start();

                    default:
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
