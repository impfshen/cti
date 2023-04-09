package com.shen.springbootwechat.Bean;

public class QryArticle {
    private String offset;      // 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
    private String count;       // 返回素材的数量，取值在1到20之间
    private String no_content;  // 1 表示不返回 content 字段，0 表示正常返回，默认为 0

    public QryArticle() {}

    public QryArticle(String offset, String count, String no_content) {
        this.offset = offset;
        this.count = count;
        this.no_content = no_content;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNo_content() {
        return no_content;
    }

    public void setNo_content(String no_content) {
        this.no_content = no_content;
    }

    @Override
    public String toString() {
        return "QryArticle{" +
                "offset='" + offset + '\'' +
                ", count='" + count + '\'' +
                ", no_content='" + no_content + '\'' +
                '}';
    }
}
