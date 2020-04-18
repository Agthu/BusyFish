package server.threads.userrequestthread;

import java.net.Socket;

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
        super.run();
    }
}
