package server.threads.userrequestthread;

import data.Comment;
import data.Product;
import server.Client;
import server.DbUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class AddCommentThread extends AbstractUserRequestThread {
    public AddCommentThread(Client client) {
        super(client);
    }

    @Override
    public void run() {
        ObjectOutputStream oos = getClient().getOos();
        ObjectInputStream ois = getClient().getOis();

        try {
            Comment comment = (Comment)ois.readObject();
            int id = comment.getProductId();
            String content = comment.getContent();
            DbUtil.AddComment(id, getClient().getUserId(), comment.getContent());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
