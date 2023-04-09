package com.shen.springbootwechat.Bean;

import java.util.List;

public class RespNewsMaterial {
    List<ItemRespNewsMaterial> articles;

    public RespNewsMaterial() {}

    public RespNewsMaterial(List<ItemRespNewsMaterial> articles) {
        this.articles = articles;
    }

    public List<ItemRespNewsMaterial> getArticles() {
        return articles;
    }

    public void setArticles(List<ItemRespNewsMaterial> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "RespNewsMaterial{" +
                "articles=" + articles.toString() +
                '}';
    }
}
