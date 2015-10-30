package com.yuanzhihao.roboticsclub;

/**
 * Created by yuanzhihao on 15/10/19.
 */
public class Admin extends User {
    String name;

    public Admin(String username, String password, String identity, String name) {
        this.username=username;
        this.password=password;
        this.identity=identity;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
