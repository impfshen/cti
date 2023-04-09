package com.shen.springbootwechat.Bean;

public class UserHistoryInfo {

    private String type;
    private String user;
    private String time;
    private String number;

    public UserHistoryInfo() {
        type = "";
        user = "";
        time = "";
        number = "";
    }

    public UserHistoryInfo(String type, String user, String time, String number) {
        this.type = type;
        this.user = user;
        this.time = time;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserHistoryInfo{" +
                "type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

}
