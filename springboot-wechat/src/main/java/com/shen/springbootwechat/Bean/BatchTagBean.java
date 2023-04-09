package com.shen.springbootwechat.Bean;

import java.util.List;

public class BatchTagBean {
    List<String> openid_list;
    int tagid;

    public BatchTagBean() {}

    public BatchTagBean(List<String> openid_list, int tagid) {
        this.openid_list = openid_list;
        this.tagid = tagid;
    }

    public List<String> getOpenid_list() {
        return openid_list;
    }

    public void setOpenid_list(List<String> openid_list) {
        this.openid_list = openid_list;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    @Override
    public String toString() {
        return "BatchTagBean{" +
                "openid_list=" + openid_list +
                ", tagid=" + tagid +
                '}';
    }
}
