package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String userId;

    public Client(Socket socket, String userId) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        this.userId = userId;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUserId() {
        return userId;
    }
}
