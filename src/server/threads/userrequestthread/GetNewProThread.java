package server.threads.userrequestthread;

import data.Product;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 获取近期商品的线程类
 * @author Lian Guan
 */
public class GetNewProThread extends AbstractUserRequestThread {
    /**
     * 构造方法
     *
     * @param client 客户端socket
     */
    public GetNewProThread(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(this.getClient().getOutputStream());
            LinkedList<Product> proList = DbUtil.getNewProducts(30);
            for(Product product: proList) {
                oos.writeObject(product);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
