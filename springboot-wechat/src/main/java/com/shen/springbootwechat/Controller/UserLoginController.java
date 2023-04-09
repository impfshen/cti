package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import com.shen.springbootwechat.Service.UserLoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;

    @GetMapping("/hasUser")
    public boolean hasUser(String username, String phone, String password){
        if (userLoginService.checkItem(username,phone,password))
            return true;
        else
            return false;
    }

    @GetMapping("/updateOpenID")
    public boolean updateOpenID(UserBasicInfo userBasicInfo){
        return userLoginService.changeOpenID(userBasicInfo);
    }

    @GetMapping("/updateDefaultID")
    public boolean updateInvalidID(String invalidid, String openid){
        return userLoginService.changeInvalidID(invalidid,openid);
    }
}
