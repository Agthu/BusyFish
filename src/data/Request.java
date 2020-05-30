package data;

import java.io.Serializable;

/**
 * 用户请求类，用于传输各种用户请求
 * @author Lian Guan
 */
public class Request implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 枚举类，请求类型
     */
    public enum RequestType {
        // 根据id获取商品
        GET_PRO_BY_ID,
        // 发布商品
        ADD_PRO,
        // 获取最新商品
        GET_NEW_PRO,
        // 根据商品id获取评论
        GET_COMMENT_BY_ID,
        //发布评论
        ADD_COMMENT,
        //发送消息
        SEND_MESSAGE
    }

    private final RequestType requestType;

    public Request(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
