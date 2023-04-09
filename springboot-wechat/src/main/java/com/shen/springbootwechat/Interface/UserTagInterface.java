package com.shen.springbootwechat.Interface;

import com.shen.springbootwechat.Bean.BatchTagBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTagInterface {

    @Autowired
    HttpInterface httpInterface;

    public String getTag(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + token;
        return httpInterface.doGet(url);
    }

    public String createTag(String token, String name){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + token;
        return httpInterface.doPost(url,"{\"tag\":{\"name\":\"" + name + "\"}}");
    }

    public String batchTaging(String token, List<String> list, Integer id){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + token;
        return httpInterface.doPost(url, new BatchTagBean(list,id));
    }

    public String batchunTaging(String token, List<String> list, Integer id){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + token;
        return httpInterface.doPost(url, new BatchTagBean(list,id));
    }

    public String getUserTag(String token, String openid){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + token;
        return httpInterface.doPost(url,"{\"openid\":\"" + openid + "\"}");
    }
}
