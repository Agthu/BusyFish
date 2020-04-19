package server.threads.userrequestthread;

import data.Product;
import server.DbUtil;

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
    public AddProThread(Socket client, Product detailedPro, String publisherId) {
        super(client);
        this.detailedPro = detailedPro;
        this.publisherId = publisherId;
    }

    @Override
    public void run() {
        try {
            DbUtil.addProduct(
                    detailedPro.getName(),
                    publisherId,
                    detailedPro.getDescription(),
                    detailedPro.getPrice()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
