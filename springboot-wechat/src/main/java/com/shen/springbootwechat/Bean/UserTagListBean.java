package com.shen.springbootwechat.Bean;

import java.util.ArrayList;
import java.util.List;

public class UserTagListBean {

    private List<Integer> tagid_list;

    public UserTagListBean() {
        tagid_list = new ArrayList<>();
    }

    public UserTagListBean(List<Integer> tagid_list) {
        this.tagid_list = tagid_list;
    }

    public List<Integer> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<Integer> tagid_list) {
        this.tagid_list = tagid_list;
    }

    @Override
    public String toString() {
        return "UserTagListBean{" +
                "tagid_list=" + tagid_list +
                '}';
    }
}
