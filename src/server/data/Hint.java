package server.data;

/**
 * 该类用于发送请求成功的提示信息，如注册成功与否等
 */
public class Hint {
    public static final long serialVersionUID = 1L;
    private boolean success;
    public Hint(boolean success) {
        this.success = success;
    }

    /**
     * 得知是否成功提示
     * @return 若为true，则相应的请求为成功
     */
    public boolean isSuccess() {
        return this.success;
    }
}
