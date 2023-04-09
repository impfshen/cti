package com.shen.springbootwechat.Bean;

public class ItemRespNewsMaterial extends ItemNewsMaterial{
    private String show_cover_pic;
    private String url;

    public ItemRespNewsMaterial() {}

    public ItemRespNewsMaterial(String show_cover_pic, String url) {
        this.show_cover_pic = show_cover_pic;
        this.url = url;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ItemRespNewsMaterial{" +
                "show_cover_pic='" + show_cover_pic + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
