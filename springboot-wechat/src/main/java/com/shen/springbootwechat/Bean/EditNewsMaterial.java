package com.shen.springbootwechat.Bean;

public class EditNewsMaterial {
    private String media_id;  // 	要修改的图文消息的id
    private String index;       // 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
    private ItemNewsMaterial articles;

    public EditNewsMaterial() {}

    public EditNewsMaterial(String media_id, String index, ItemNewsMaterial articles) {
        this.media_id = media_id;
        this.index = index;
        this.articles = articles;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public ItemNewsMaterial getArticles() {
        return articles;
    }

    public void setArticles(ItemNewsMaterial articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "EditNewsMaterial{" +
                "media_id='" + media_id + '\'' +
                ", index='" + index + '\'' +
                ", articles=" + articles +
                '}';
    }
}
