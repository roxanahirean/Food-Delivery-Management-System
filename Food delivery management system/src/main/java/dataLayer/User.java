package dataLayer;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String username;
    private String password;

    public User(){};
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
