package client.data;

/**
 * 账户类， 用于登录时传输对象
 */
public class LoginAccount {
    private String id;
    private String password;

    /**
     * 构造方法
     * @param id 账号
     * @param password 密码
     */
    public LoginAccount(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
