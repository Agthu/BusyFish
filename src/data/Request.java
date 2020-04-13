package data;

import java.io.Serializable;

/**
 * 用户请求类，用于传输各种用户请求
 */
public class Request implements Serializable {
    public static final long serialVersionUID = 1L;
    // 枚举类，请求类型
    public enum RequestType {
        GET_PRO_BY_ID,
        ADD_PRO,

    }

    private RequestType requestType;

    public Request(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
