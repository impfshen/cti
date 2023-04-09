package com.shen.springbootwechat.Bean;

public class UserPersonalInfo {
    private String phone;
    private String sex;
    private String city;
    private String email;
    private String profession;

    public UserPersonalInfo() {
        phone = "";
        sex = "";
        city = "";
        email = "";
        profession = "";
    }

    public UserPersonalInfo(String phone, String sex, String city, String email, String profession) {
        this.phone = phone;
        this.sex = sex;
        this.city = city;
        this.email = email;
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "UserPersonalInfo{" +
                "phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
