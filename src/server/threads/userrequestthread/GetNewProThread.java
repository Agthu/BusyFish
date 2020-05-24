package server.threads.userrequestthread;

import data.Product;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 获取最新商品的线程类
 * @author Lian Guan
 */
public class GetNewProThread extends AbstractUserRequestThread {
    public GetNewProThread(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(getClient().getOutputStream());
            LinkedList<Product> proList = DbUtil.getNewProducts(10);
            for(Product product: proList) {
                oos.writeObject(product);
            }
            oos.writeObject(new Product(-1));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
