package server.threads.userrequestthread;

import data.Message;
import server.Client;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

/**
 * 发送消息的线程
 */
public class SendMessageThread extends AbstractUserRequestThread {

    public SendMessageThread(Client client) {
        super(client);
    }

    @Override
    public void run() {
        ObjectOutputStream oos = getClient().getOos();
        ObjectInputStream ois = getClient().getOis();
        try {
            Message message = (Message)ois.readObject();
            DbUtil.sendMessage(getClient().getUserId(), message.getTo(), message.getContent());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
