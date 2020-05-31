package data;

import java.io.Serializable;

/**
 * 消息
 */
public class Message implements Serializable {
    public static final long serialVersionUID = 1L;
    private String to;
    private String content;

    /**
     *
     * @param to 接收者
     * @param content 内容
     */
    public Message(String to, String content) {
        this.to = to;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }
}
