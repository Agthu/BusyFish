package data;

import java.io.Serializable;

/**
 * 客户端和服务器之间传输对象，公共父类
 * @author Lian Guan
 */
public class Message implements Serializable {
    private MessageType messageType;

}
