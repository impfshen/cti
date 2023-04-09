package com.shen.springbootwechat.Controller;

import com.alibaba.fastjson.JSON;
import com.shen.springbootwechat.Bean.UserTagListBean;
import com.shen.springbootwechat.Interface.UserTagInterface;
import com.shen.springbootwechat.Service.WeChatService;
import com.shen.springbootwechat.Util.WeChatContant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class UserTagController {

    @Resource
    WeChatService weChatService;

    @Resource
    UserTagInterface userTagInterface;

    // link = { http://47.111.1.11/tag/getTagList }
    @GetMapping("/getTagList")
    public String getTagList(){
        String token = weChatService.getAccessToken();
        return userTagInterface.getTag(token);
    }

    // link = { http://47.111.1.11/tag/getUserTag?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/getUserTag")
    public Integer getUserTag(String openid){
        String token = weChatService.getAccessToken();
        UserTagListBean userTagListBean;
        try {
            String json = userTagInterface.getUserTag(token,openid);
            userTagListBean = JSON.parseObject(json,UserTagListBean.class);
        } catch (Exception e) {
            userTagListBean = new UserTagListBean();
        }
        List<Integer> list = userTagListBean.getTagid_list();
        if (!list.isEmpty())
            return list.get(0);
        else
            return -1;  //表示当前用户没有标签
    }

    // link = { http://47.111.1.11/tag/batchTaging?openid=oT7V954WZK9gzaKsbBneH0yD17oc&id=ID }
    @GetMapping("/batchTaging")
    public String batchTaging(String openid, Integer id){
        String token = weChatService.getAccessToken();
        List<String> list = new ArrayList<>();
        list.add(openid);
        return userTagInterface.batchTaging(token,list,id);
    }

    // link = { http://47.111.1.11/tag/batchunTaging?openid=oT7V954WZK9gzaKsbBneH0yD17oc&id=ID }
    @GetMapping("/batchunTaging")
    public String batchunTaging(String openid, Integer id){
        String token = weChatService.getAccessToken();
        List<String> list = new ArrayList<>();
        list.add(openid);
        return userTagInterface.batchunTaging(token,list,id);
    }

    // link = { http://47.111.1.11/tag/tagToVisitor?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/tagToVisitor")
    public void tagToVisitor(String openid){
        tagToNull(openid);
        batchTaging(openid,WeChatContant.TAG_ID_VISITOR);
    }

    // link = { http://47.111.1.11/tag/tagToUser?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/tagToUser")
    public void tagToUser(String openid){
        tagToNull(openid);
        batchTaging(openid,WeChatContant.TAG_ID_USER);
    }

    // link = { http://47.111.1.11/tag/tagToManager?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/tagToManager")
    public void tagToManager(String openid){
        tagToNull(openid);
        batchTaging(openid,WeChatContant.TAG_ID_MANAGER);
    }

    // link = { http://47.111.1.11/tag/tagToRunner?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/tagToRunner")
    public void tagToRunner(String openid){
        tagToNull(openid);
        batchTaging(openid,WeChatContant.TAG_ID_RUNNER);
    }

    // link = { http://47.111.1.11/tag/tagToNull?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/tagToNull")
    public void tagToNull(String openid){
        batchunTaging(openid, WeChatContant.TAG_ID_VISITOR);
        batchunTaging(openid, WeChatContant.TAG_ID_USER);
        batchunTaging(openid, WeChatContant.TAG_ID_MANAGER);
        batchunTaging(openid, WeChatContant.TAG_ID_RUNNER);
    }
}
