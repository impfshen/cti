package com.shen.springbootwechat.Service;

import com.alibaba.fastjson.JSONObject;
import com.shen.springbootwechat.Bean.LinkItem;
import com.shen.springbootwechat.Controller.WeChatTestController;
import com.shen.springbootwechat.Interface.UserTagInterface;
import com.shen.springbootwechat.Util.RedisUtil;
import com.shen.springbootwechat.Util.WeChatContant;
import com.shen.springbootwechat.Util.WeChatUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WeChatServiceImpl implements WeChatService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    RedisUtil redisUtil;

    @Resource
    UserTagInterface userTagInterface;

    public String processRequest(HttpServletRequest request){
        String respXml;
        try{
            Map<String,String> reqMap = WeChatUtil.parseXml(request);
            String msgType = reqMap.get(WeChatContant.MsgType);
            if (msgType.equals(WeChatContant.MESSAGE_TYPE_TEXT)) {
                String message = reqMap.get(WeChatContant.Content);
                String respContent = null;
                if (message.equals("我的信息")){
                    respContent = getUserInfo(reqMap.get(WeChatContant.FromUserName));
                    respXml = WeChatUtil.sendTextMsg(reqMap, respContent);
                } else if (message.equals("开发文档")) {
                    List<LinkItem> items = new ArrayList<>();
                    LinkItem item = new LinkItem();
                    item.setTitle("开发文档");
                    item.setDescription("JAVA FreeSWITCH ESL 开发指南");
                    item.setPicUrl("http://47.111.1.11/image/cover.jpg");
                    item.setUrl("http://www.ddrj.com/blogs/2021/11/20/java-esl-guide/index.html");
                    items.add(item);
                    respXml = WeChatUtil.sendArticleMsg(reqMap, items);
                } else {
                    respContent = reqMap.get(WeChatContant.Content);
                    respXml = WeChatUtil.sendTextMsg(reqMap, respContent);
                }
            } else if (msgType.equals(WeChatContant.MESSAGE_TYPE_IMAGE)) {
                String mId = reqMap.get(WeChatContant.MediaId);
                respXml = WeChatUtil.sendImageMsg(reqMap, mId);
            } else if (msgType.equals(WeChatContant.MESSAGE_TYPE_VOICE)) {
                String mId = reqMap.get(WeChatContant.MediaId);
                respXml = WeChatUtil.sendVoiceMsg(reqMap, mId);
            } else if (msgType.equals(WeChatContant.MESSAGE_TYPE_EVENT)) {
                String event = reqMap.get(WeChatContant.Event);
                if (event.equals(WeChatContant.EVENT_TYPE_CLICK)){
                    String eventkey = reqMap.get(WeChatContant.EventKey);
                    if (eventkey.equals("我的信息")){
                        String respContent = getUserInfo(reqMap.get(WeChatContant.FromUserName));
                        respXml = WeChatUtil.sendTextMsg(reqMap, respContent);
                    } else respXml = WeChatContant.DEFAULT_RESPONSE;
                } else if (event.equals(WeChatContant.EVENT_TYPE_SUBSCRIBE)) {
                    String openid = reqMap.get(WeChatContant.FromUserName);
                    userTaging(openid, WeChatContant.TAG_ID_VISITOR);
                    String respContent = WeChatContant.WELCOME_RESPONSE;
                    respXml = WeChatUtil.sendTextMsg(reqMap, respContent);
                } else if (event.equals(WeChatContant.EVENT_TYPE_UNSUBSCRIBE)) {
                    String openid = reqMap.get(WeChatContant.FromUserName);
                    userunTaging(openid, WeChatContant.TAG_ID_VISITOR);
                    userunTaging(openid, WeChatContant.TAG_ID_USER);
                    userunTaging(openid, WeChatContant.TAG_ID_MANAGER);
                    userunTaging(openid, WeChatContant.TAG_ID_RUNNER);
                    respXml = WeChatContant.DEFAULT_RESPONSE;
                } else respXml = WeChatContant.DEFAULT_RESPONSE;
            } else respXml = WeChatContant.DEFAULT_RESPONSE;
            return respXml;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void creatAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String path = "?grant_type={grant_type}&appid={appid}&secret={secret}";
        Map<String, String> params = new HashMap<>(3);
        params.put("grant_type", "client_credential");
        params.put("appid", WeChatContant.appID);
        params.put("secret", WeChatContant.secret);

        ResponseEntity<String> forObject = restTemplate.getForEntity(url + path, String.class, params);

        JSONObject jsonObject = JSONObject.parseObject(forObject.getBody());

        String access_Token = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");

        if (access_Token != null && expires_in != null) {
            redisUtil.set(WeChatContant.ACCESS_TOKEN_KEY, access_Token, Long.parseLong(expires_in), TimeUnit.SECONDS);
        } else {
            redisUtil.set(WeChatContant.ERROR_KEY, forObject.getBody());
        }
    }

    @Override
    public String getAccessToken() {
        if (!redisUtil.hasKey(WeChatContant.ACCESS_TOKEN_KEY))
            creatAccessToken();
        return (String) redisUtil.get(WeChatContant.ACCESS_TOKEN_KEY);
    }

    @Override
    public String getUserInfo(String openid){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        String path = "?access_token={access_token}&openid={openid}&lang=zh_CN";
        Map<String, String> params = new HashMap<>(2);
        params.put("access_token", getAccessToken());
        params.put("openid", openid);

        ResponseEntity<String> forObject = restTemplate.getForEntity(url + path, String.class, params);
        JSONObject jsonObject = JSONObject.parseObject(forObject.getBody());

        return jsonObject.toString();
    }

    public void userTaging(String openid, Integer id){
        String token = getAccessToken();
        List<String> list = new ArrayList<>();
        list.add(openid);
        userTagInterface.batchTaging(token,list,id);
    }

    public void userunTaging(String openid, Integer id){
        String token = getAccessToken();
        List<String> list = new ArrayList<>();
        list.add(openid);
        userTagInterface.batchunTaging(token,list,id);
    }
}
