package com.shen.springbootwechat.Bean;

import java.util.List;

public class NewsMaterial {
    List<ItemNewsMaterial> articles;

    public NewsMaterial() {}

    public NewsMaterial(List<ItemNewsMaterial> articles) {
        this.articles = articles;
    }

    public List<ItemNewsMaterial> getArticles() {
        return articles;
    }

    public void setArticles(List<ItemNewsMaterial> articles) {
        this.articles = articles;
    }
}
