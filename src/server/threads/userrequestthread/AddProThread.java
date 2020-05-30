package server.threads.userrequestthread;

import data.Hint;
import data.Product;
import server.Client;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * @author Lian Guan
 */
public class AddProThread extends AbstractUserRequestThread{

    /**
     * 要发布的商品的详细信息
     */
    Product detailedPro;

    /**
     * 发布者的id
     */
    String publisherId;

    /**
     * 构造方法
     *
     * @param client 客户端socket
     * @param detailedPro 商品的详细信息
     * @param publisherId 发布者的id
     */
    public AddProThread(Client client, Product detailedPro, String publisherId) {
        super(client);
        this.detailedPro = detailedPro;
        this.publisherId = publisherId;
    }

    @Override
    public void run() {
        ObjectOutputStream oos;
        try {
            oos = getClient().getOos();
            try {
                if(DbUtil.addProduct(
                        detailedPro.getName(),
                        // 根据客户端的用户来确定publisherId
                        publisherId,
                        detailedPro.getDescription(),
                        detailedPro.getPrice()
                )) {
                    oos.writeObject(new Hint(true));
                }
            } catch (SQLException | IOException e) {
                oos.writeObject(new Hint(false));
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
