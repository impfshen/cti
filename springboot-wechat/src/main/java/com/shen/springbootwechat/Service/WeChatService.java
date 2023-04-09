package com.shen.springbootwechat.Service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {

    String processRequest(HttpServletRequest request);

    void creatAccessToken();

    String getAccessToken();

    String getUserInfo(String openid);

}
