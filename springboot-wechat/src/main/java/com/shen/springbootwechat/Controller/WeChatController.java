package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Service.WeChatService;
import com.shen.springbootwechat.Util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class WeChatController {

    @Resource
    private WeChatService weChatService;

    @GetMapping(value="wechat")
    public String wxToken(@RequestParam(value="signature")String signature,
                          @RequestParam(value="timestamp")String timestamp,
                          @RequestParam(value="nonce")String nonce,
                          @RequestParam(value="echostr")String echostr)
    {
        return WeChatUtil.signature(signature,timestamp,nonce)?echostr:null;
    }

    @PostMapping(value="wechat")
    public String post(HttpServletRequest request){
        return weChatService.processRequest(request);
    }

}
