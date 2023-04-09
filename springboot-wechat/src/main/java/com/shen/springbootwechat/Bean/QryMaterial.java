package com.shen.springbootwechat.Bean;

public class QryMaterial {
    private String type;        //素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
    private String offset;      // 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
    private String count;       // 返回素材的数量，取值在1到20之间

    public QryMaterial() {}

    public QryMaterial(String type, String offset, String count) {
        this.type = type;
        this.offset = offset;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
