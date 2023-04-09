package com.shen.springbootwechat.Bean;

public class UserBasicInfo {
    private String username;
    private String phone;
    private String password;
    private String openid;

    public UserBasicInfo() {
        username = "";
        phone = "";
        password = "";
        openid = "";
    }

    public UserBasicInfo(String username, String phone, String password, String openid) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.openid = openid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "UserBasicInfo{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }

    public boolean isEmpty() {
        if (username.equals("") && phone.equals("") && password.equals("") && openid.equals(""))
            return true;
        else
            return false;
    }

    public boolean hasEmpty() {
        if (username.equals("") || phone.equals("") || password.equals(""))
            return true;
        else
            return false;
    }

}
