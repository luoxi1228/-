package ChatSystem;

import java.io.Serializable;

//用户类
public class User implements Serializable {//序列化
    private static final long serialVersionUID=1L;
    private String userId;//用户id或用户名
    private String password;//用户密码

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
