package com.shen.springbootwechat.Controller;

import com.alibaba.fastjson.JSON;
import com.shen.springbootwechat.Interface.HttpInterface;
import com.shen.springbootwechat.Util.WeChatContant;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import com.sun.net.httpserver.Authenticator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.spi.DirStateFactory;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

@RestController
@RequestMapping("/authorize")
public class UserAuthorizeController {

    @Resource
    HttpInterface httpInterface;

    @Resource
    UserController userController;

    // link = { http://localhost/authorize/getURL }
    @GetMapping("/getURL")
    public String getURL() throws IOException {
        String uRLEncoderAuthCallBack = URLEncoder.encode(WeChatContant.auth_call_back, "utf-8");
        System.out.println(uRLEncoderAuthCallBack);
        String apiAuthUrl = WeChatContant.api_auth
                .replace("APPID", WeChatContant.appID)
                .replace("REDIRECT_URI", uRLEncoderAuthCallBack)
                .replace("SCOPE", "snsapi_base")
                .replace("STATE", "state");
        return apiAuthUrl;
    }

    // link = { http://localhost/authorize/getOpenID }
    @GetMapping("/getOpenID")
    public void getOpenID(HttpServletResponse response, String state) throws IOException {
        String uRLEncoderAuthCallBack = URLEncoder.encode(WeChatContant.auth_call_back, "utf-8");
        // 用户授权跳转地址
        String apiAuthUrl = WeChatContant.api_auth
                .replace("APPID", WeChatContant.appID)
                .replace("REDIRECT_URI", uRLEncoderAuthCallBack)
                .replace("SCOPE", "snsapi_base")
                .replace("STATE", state);
        response.sendRedirect(apiAuthUrl);
    }

    @GetMapping("/authCallBack")
    public void authCallBack(HttpServletResponse response, String code, String state) throws IOException {
        if (StringUtils.isEmpty(code)) return;
        //获取微信授权access_token
        String apiAuthAccessTokenUrl = WeChatContant.api_auth_access_token
                .replace("APPID", WeChatContant.appID)
                .replace("SECRET", WeChatContant.secret)
                .replace("CODE", code);
        String result = httpInterface.doGet(apiAuthAccessTokenUrl);
        HashMap<String, String> wechatAuthTokenMap = JSON.parseObject(result, HashMap.class);
        String openid = wechatAuthTokenMap.get("openid");
        if (state.equals("Register"))
            response.sendRedirect("http://47.111.1.11/user/toRegister?openid="+openid);
        else if (state.equals("Login"))
            response.sendRedirect("http://47.111.1.11/user/toLogin?openid="+openid);
        else if (state.equals("unLogin"))
            response.sendRedirect("http://47.111.1.11/user/unLogin?openid="+openid);
        else if (state.equals("User"))
            response.sendRedirect("http://47.111.1.11/user/toUserHome?openid="+openid);
        else if (state.equals("Manager"))
            response.sendRedirect("http://47.111.1.11/user/toManagerHome?openid="+openid);
        else if (state.equals("Runner"))
            response.sendRedirect("http://47.111.1.11/user/toRunnerHome?openid="+openid);
        else return;
    }
}
