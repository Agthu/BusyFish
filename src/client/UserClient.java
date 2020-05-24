package client;

import data.Hint;
import data.Product;
import data.Request;
import data.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @author Lian Guan
 */
public class UserClient {
    public static final String HOST = "127.0.0.1";
    public static final int REGISTER_PORT = 9810;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public UserClient(Socket socket) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * 注册
     * @param id 用户名
     * @param name 昵称
     * @param password 密码
     * @return true成功，false失败
     */
    public static boolean register(String id, String name, String password) throws IOException, ClassNotFoundException {
        Socket registerSocket = new Socket(HOST, REGISTER_PORT);
        ObjectOutputStream oos = new ObjectOutputStream(registerSocket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(registerSocket.getInputStream());
        oos.writeObject(new User(id, name, password));

        Hint hint = (Hint)ois.readObject();
        return hint.isSuccess();
    }

    /**
     * 根据id获取商品的详细信息
     * @param id 商品id
     * @return 商品的详细信息
     */
    public Product getProById(int id) throws IOException, ClassNotFoundException {
        oos.writeObject(new Request(Request.RequestType.GET_PRO_BY_ID));
        oos.writeObject(new Product(id));
        return (Product)ois.readObject();
    }

    /**
     * 获取最新商品
     * @return 最新商品列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public LinkedList<Product> getNewProduct() throws IOException, ClassNotFoundException {
        oos.writeObject(new Request(Request.RequestType.GET_NEW_PRO));
        LinkedList<Product> proList = new LinkedList<>();
        while(true) {
            Product product = (Product)ois.readObject();
            if(product.getId() != -1) {
                proList.add(product);
            }
            else {
                break;
            }
        }
        return proList;
    }
}
