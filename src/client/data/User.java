package client.data;

/**
 * 用户类，用于传输对象
 */
public class User {
    private String id;
    private String name;
    private String password;

    /**
     * 构造方法，用于登录时传输对象
     * @param id 用户名
     * @param password 密码
     */
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * 构造方法，用于注册时传输对象
     * @param id 用户名
     * @param name 昵称
     * @param password 密码
     */
    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * getter方法
     * @return 用户名
     */
    public String getId() {
        return id;
    }

    /**
     * getter方法
     * @return 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * getter方法
     * @return 密码
     */
    public String getPassword() {
        return password;
    }
}
