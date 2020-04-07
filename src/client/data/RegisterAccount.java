package client.data;

/**
 * 账户类，用于注册时传输对象
 */
public class RegisterAccount {
    private String id;
    private String name;
    private String password;

    /**
     * 构造方法
     * @param id 用户名
     * @param name 昵称
     * @param password 密码
     */
    public RegisterAccount(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
