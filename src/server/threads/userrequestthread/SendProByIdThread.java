package server.threads.userrequestthread;

import data.Product;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * 根据商品id来发送一个商品的信息
 * @author Lian Guan
 */
public class SendProByIdThread extends AbstractUserRequestThread {

    private int id;

    public SendProByIdThread(Socket client, int id) {
        super(client);
        this.id = id;
    }

    /**
     * TODO 向客户端发送商品信息
     */
    @Override
    public void run() {
        ObjectOutputStream oos;
        ObjectInputStream ois;
        try {
            // 对象输入、输出流
            oos = new ObjectOutputStream(this.getClient().getOutputStream());
            ois = new ObjectInputStream(this.getClient().getInputStream());

            Product product = (Product)ois.readObject();
            // 根据id从数据库中获取商品信息，并输出
            oos.writeObject(DbUtil.getProductById(product.getId()));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
