package server.threads.userrequestthread;

import data.Product;
import server.Client;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

/**
 * 购买商品的线程
 */
public class BuyProductThread extends AbstractUserRequestThread{
    public BuyProductThread(Client client) {
        super(client);
    }

    @Override
    public void run() {
        ObjectOutputStream oos = getClient().getOos();
        ObjectInputStream ois = getClient().getOis();
        try {
            Product product = (Product)ois.readObject();
            DbUtil.buyProduct(getClient().getUserId(), product.getId());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
