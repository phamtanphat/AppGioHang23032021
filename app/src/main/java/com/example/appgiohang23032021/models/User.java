package com.example.appgiohang23032021.models;

public class User {
    private String email;
    private String password;
    private boolean isLogin;

    public User(String email, String password, boolean isLogin) {
        this.email = email;
        this.password = password;
        this.isLogin = isLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

}
