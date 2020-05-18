package client;

import data.Hint;
import data.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserClient {
    private Socket socket;

    public UserClient(Socket socket) {
        this.socket = socket;
    }

    /**
     * 注册
     * @param id 用户名
     * @param name 昵称
     * @param password 密码
     * @return true成功，false失败
     */
    public static boolean register(String id, String name, String password) throws IOException, ClassNotFoundException {
        Socket registerSocket = new Socket("127.0.0.1", 9810);
        ObjectOutputStream oos = new ObjectOutputStream(registerSocket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(registerSocket.getInputStream());
        oos.writeObject(new User(id, name, password));

        Hint hint = (Hint)ois.readObject();
        return hint.isSuccess();
    }
}
