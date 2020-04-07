package client.data;

/**
 * 账户类， 用于传输对象
 * @var id 账号
 * @var password 密码
 */
public class Account {
    private String id;
    private String password;

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
