package com.shen.springbootwechat.Bean;

import java.util.List;

public class MenuOne {
    private String name;
    private List<MenuTwo> sub_button;
    private String type;
    private String key;
    private String url;
    private String media_id;
    private String appid;
    private String pagepath;

    public MenuOne() {}

    public MenuOne(String name, List<MenuTwo> sub_button, String type, String key,
                   String url, String media_id, String appid, String pagepath) {
        this.name = name;
        this.sub_button = sub_button;
        this.type = type;
        this.key = key;
        this.url = url;
        this.media_id = media_id;
        this.appid = appid;
        this.pagepath = pagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuTwo> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuTwo> sub_button) {
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    @Override
    public String toString() {
        return "MenuOne{" +
                "name='" + name + '\'' +
                ", sub_button=" + sub_button +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", media_id='" + media_id + '\'' +
                ", appid='" + appid + '\'' +
                ", pagepath='" + pagepath + '\'' +
                '}';
    }
}
